package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.Constantes;
import com.cpe.emergencymanager.model.AlertEntity;
import com.cpe.emergencymanager.model.FireEntity;
import com.cpe.emergencymanager.model.LocalizedEntity;
import com.cpe.emergencymanager.model.enums.ActionStatus;
import com.cpe.emergencymanager.model.enums.ResourceStatus;
import com.cpe.emergencymanager.repository.FireRepository;
import com.cpe.emergencymanager.util.CoordinateUtil;
import lombok.extern.slf4j.Slf4j;
import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.FeatureCollection;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Polygon;
import mil.nga.sf.geojson.Position;
import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class FireService {
    private final ResourceService resourceService;
    private final FireRepository fireRepository;
    private final InterventionService interventionService;

    public FireService(ResourceService resourceService, FireRepository fireRepository, InterventionService interventionService) {
        this.resourceService = resourceService;
        this.fireRepository = fireRepository;
        this.interventionService = interventionService;
    }

    /**
     * Calcul le nouveau centre d'un feu en fonction des alerts qu'il a reçu
     * @param fire
     * @return
     */
    public FireEntity setFireCenter(FireEntity fire) {
        List<Coordinate> alertsCoordinates = new ArrayList<>();
        for(AlertEntity alert : fire.getAlerts()) {
            alertsCoordinates.add(new Coordinate(alert.getLatitude(), alert.getLongitude()));
        }
        Coordinate coordinate = CoordinateUtil.getCentroidFromCoordinates(alertsCoordinates);
        fire.setLatitude(coordinate.y);
        fire.setLongitude(coordinate.x);
        return fire;
    }

    /**
     * Récupère l'alert la plus loin du centre du feu
     * @param fire
     * @return
     */
    public double getFarestAlertFromFire(FireEntity fire) {
        if(fire.getAlerts().size() == 0) {
            return 0;
        }
        AtomicReference<AlertEntity> alert = new AtomicReference<>((AlertEntity) fire.getAlerts().toArray()[0]);
        AtomicReference<Double> distanceAlert = new AtomicReference<>(CoordinateUtil.distance((LocalizedEntity) fire, (LocalizedEntity) alert, 'K'));
        fire.getAlerts().forEach(alert1 -> {
            Double newAlertDistance = CoordinateUtil.distance((LocalizedEntity) fire, (LocalizedEntity) alert1, 'K');
            if(newAlertDistance > distanceAlert.get()) {
                distanceAlert.set(newAlertDistance);
                alert.set(alert1);
            }
        });
        return distanceAlert.get();
    }

    /**
     * Regarde si l'alerte est une alerte d'un nouveau feu
     * Si c'est le cas création d'un nouveau feu, sinon ajout à l'existant
     * @param alert
     */
    public void detectAlert(AlertEntity alert) {
        // Voir les feux existants
        // Si un feu est à moins de 100m, on ajoute l'alerte à ce feu
        // Sinon, on crée un nouveau feu
        // TODO : Stocker en cache la liste des feus
        // Pour éviter de devoir récupérer la liste des feux à chaque alerte
        log.info("Traitement de l'alerte {}", alert.getId());
        List<FireEntity> fires = this.fireRepository.findAll();
        FireEntity fireEntity = null;
        for(FireEntity fire : fires) {
            // Si l'alert est dans le radius d'un feu déjà existant on ajoute l'alerte à ce feu
            if (CoordinateUtil.distance((LocalizedEntity) fire, (LocalizedEntity) alert, 'K') < Constantes.FIRE_DETECTION_RADIUS) {
                fireEntity = fire;
                fireEntity.getAlerts().add(alert);
                fireEntity = this.setFireCenter(fireEntity);
                // L'intensité correspond à la distance avec l'alert la plus loin
                fireEntity.setIntensity((int) this.getFarestAlertFromFire(fireEntity));
                this.fireRepository.save(fireEntity);
                log.info("Alerte ajoutée au feu existant {}", fireEntity.getId());
            }
        }
        if(fireEntity == null) {
            fireEntity = new FireEntity();
            fireEntity.setLatitude(alert.getLatitude());
            fireEntity.setLongitude(alert.getLongitude());
            fireEntity.setAlertsById(List.of(alert));
            this.fireRepository.save(fireEntity);
            log.info("Nouveau feu créé {}", fireEntity.getId());
        }
        this.interventionService.triggerIntervention(fireEntity);
    }

    /**
     * Met fin à un feu et à toutes ses interventions en cours
     * @param fireId
     * @return
     */
    public FireEntity endFire(int fireId) {
        log.info("Fin du feu {}", fireId);
        FireEntity fire = this.fireRepository.findById(fireId);
        fire.getInterventions().forEach(interventionEntity -> {
            interventionEntity.setStatus(ActionStatus.FINISHED);
            interventionEntity.getTrucks().forEach(truckEntity -> {
                truckEntity.setStatus(ResourceStatus.AVAILABLE);
                this.resourceService.saveTruck(truckEntity);
            });
            this.interventionService.saveIntervention(interventionEntity);
        });
        fire.setStatus(ActionStatus.FINISHED);
        log.info("Toutes les ressources ont été libérées pour le feu {}", fireId);
        return fireRepository.save(fire);
    }

    public FireEntity addFire(FireEntity fireEntity) {
        return this.fireRepository.save(fireEntity);
    }

    public void deleteFire(int fireId) {
        this.fireRepository.deleteById(fireId);
    }

    public FireEntity getFire(int fireId) {
        return this.fireRepository.findById(fireId);
    }

    public FireEntity editFire(FireEntity fireEntity) {
        return this.fireRepository.save(fireEntity);
    }

    public List<FireEntity> getFires() {
        return this.fireRepository.findAll();
    }

    public FeatureCollection getAllFireGeoPoints() {
        List<FireEntity> list = this.fireRepository.findAll();
        FeatureCollection featureCollection = new FeatureCollection();
        for (FireEntity fire : list) {
            Feature feature = new Feature();
            Map<String, Object> properties = new HashMap<String, Object>();
            Point geometry = new Point();
            geometry.setCoordinates(new Position(fire.getLongitude(), fire.getLatitude()));
            properties.put("id", fire.getId());
            properties.put("type", "FIRE");
            feature.setGeometry(geometry);
            feature.setProperties(properties);
            featureCollection.addFeature(feature);
        }
        return featureCollection;
    }

    public FeatureCollection getAllFiresGeoPolygon() {
        List<FireEntity> list = this.fireRepository.findAll();
        FeatureCollection featureCollection = new FeatureCollection();
        for (FireEntity fire : list) {
            Feature feature = new Feature();
            Map<String, Object> properties = new HashMap<String, Object>();
            Polygon polygon = new Polygon();
            List<Position> positions = new ArrayList<Position>();
            Integer NUM_POINTS = 30;
            for (int i = 0; i < NUM_POINTS; i++) {
                double angle = 360.0 / NUM_POINTS * i;
                double latitude = fire.getLatitude() + 0.00001*fire.getIntensity()* Math.cos(Math.toRadians(angle));
                double longitude = fire.getLongitude() + 0.00001*fire.getIntensity() * Math.sin(Math.toRadians(angle));
                positions.add(new Position(latitude, longitude));
            }
            List<List<Position>> cord = new ArrayList<>();
            cord.add(positions);
            polygon.setCoordinates(cord);
            properties.put("id", fire.getId());
            properties.put("type", "FIRE");
            feature.setGeometry(polygon);
            feature.setProperties(properties);
            featureCollection.addFeature(feature);
        }
        return featureCollection;
    }
}
