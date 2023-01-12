package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.model.FiremanEntity;
import com.cpe.emergencymanager.model.StationEntity;
import com.cpe.emergencymanager.model.TruckEntity;
import com.cpe.emergencymanager.repository.FiremanRepository;
import com.cpe.emergencymanager.repository.StationRepository;
import com.cpe.emergencymanager.repository.TruckRepository;
import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.FeatureCollection;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Position;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceService {
    private final FiremanRepository firemanRepository;
    private final TruckRepository truckRepository;
    private final StationRepository stationRepository;

    public ResourceService(FiremanRepository firemanRepository, TruckRepository truckRepository, StationRepository stationRepository) {
        this.firemanRepository = firemanRepository;
        this.truckRepository = truckRepository;
        this.stationRepository = stationRepository;
    }

    public FiremanEntity saveFireman(FiremanEntity firemanEntity) {
        return this.firemanRepository.save(firemanEntity);
    }

    public void deleteFireman(int firemanId) {
        this.firemanRepository.deleteById(firemanId);
    }

    public FiremanEntity getFireman(int firemanId) {
        return this.firemanRepository.findById(firemanId);
    }

    public TruckEntity saveTruck(TruckEntity truckEntity) {
        return this.truckRepository.save(truckEntity);
    }

    public TruckEntity getTruck(int truckId) {
        return this.truckRepository.findById(truckId);
    }

    public void deleteTruck(int truckId) {
        this.truckRepository.deleteById(truckId);
    }

    public StationEntity saveStation(StationEntity stationEntity) {
        return this.stationRepository.save(stationEntity);
    }

    public StationEntity getStation(int stationId) {
        return this.stationRepository.findById(stationId);
    }

    public void deleteStation(int stationId) {
        this.stationRepository.deleteById(stationId);
    }

    public List<FiremanEntity> getAllFireman() {
        return this.firemanRepository.findAll();
    }

    public List<StationEntity> getAllStations() {
        return this.stationRepository.findAll();
    }

    public List<TruckEntity> getAllTrucks() {
        return this.truckRepository.findAll();
    }

    public FeatureCollection getAllStationsGeo() {
        List<StationEntity> list = this.stationRepository.findAll();
        FeatureCollection featureCollection = new FeatureCollection();
        for (StationEntity station : list) {
            Feature feature = new Feature();
            Map<String, Object> properties = new HashMap<String, Object>();
            Point geometry = new Point();
            geometry.setCoordinates(new Position(station.getLongitude(), station.getLatitude()));
            properties.put("id", station.getId());
            properties.put("type", "STATION");
            feature.setGeometry(geometry);
            feature.setProperties(properties);
            featureCollection.addFeature(feature);
        }
        return featureCollection;
    }

    public FeatureCollection getAllTrucksGeo() {
        List<TruckEntity> list = this.truckRepository.findAll();
        FeatureCollection featureCollection = new FeatureCollection();
        for (TruckEntity truck : list) {
            Feature feature = new Feature();
            Map<String, Object> properties = new HashMap<String, Object>();
            Point geometry = new Point();
            geometry.setCoordinates(new Position(truck.getLongitude(), truck.getLatitude()));
            properties.put("id", truck.getId());
            properties.put("type", "TRUCKS");
            feature.setGeometry(geometry);
            feature.setProperties(properties);
            featureCollection.addFeature(feature);
        }
        return featureCollection;
    }
}
