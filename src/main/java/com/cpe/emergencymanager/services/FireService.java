package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.Constantes;
import com.cpe.emergencymanager.model.AlertEntity;
import com.cpe.emergencymanager.model.FireEntity;
import com.cpe.emergencymanager.model.LocalizedEntity;
import com.cpe.emergencymanager.repository.FireRepository;
import com.cpe.emergencymanager.util.CoordinateUtil;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void detectAlert(AlertEntity alert) {
        // Voir les feux existants
        // Si un feu est à moins de 100m, on ajoute l'alerte à ce feu
        // Sinon, on crée un nouveau feu
        // TODO : Stocker en cache la liste des feus
        // Pour éviter de devoir récupérer la liste des feux à chaque alerte
        List<FireEntity> fires = this.fireRepository.findAll();
        FireEntity fireEntity = null;
        for(FireEntity fire : fires) {
            // Si l'alert est dans le radius d'un feu déjà existant on ajoute l'alerte à ce feu
            if (CoordinateUtil.distance((LocalizedEntity) fire, (LocalizedEntity) alert, 'K') < Constantes.FIRE_DETECTION_RADIUS) {
                fireEntity = fire;
                fireEntity.getAlerts().add(alert);
                // TODO : Calculer une intensité en fonction des alertes
                fireEntity.setIntensity(alert.getIntensity());
                this.fireRepository.save(fireEntity);
            }
        }
        if(fireEntity == null) {
            fireEntity = new FireEntity();
            fireEntity.setLatitude(alert.getLatitude());
            fireEntity.setLongitude(alert.getLongitude());
            fireEntity.setAlertsById(List.of(alert));
            this.fireRepository.save(fireEntity);
        }
        this.interventionService.triggerIntervention(fireEntity);
    }

    public Boolean isAlertInFire(FireEntity fire, AlertEntity alert) {
        if(CoordinateUtil.distance((LocalizedEntity) fire, (LocalizedEntity) alert, 'K') < fire.getRadius()) {
            return true;
        }
        return false;
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
}
