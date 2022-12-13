package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.model.FiremanEntity;
import com.cpe.emergencymanager.model.TruckEntity;
import com.cpe.emergencymanager.repository.FiremanRepository;
import com.cpe.emergencymanager.repository.StationRepository;
import com.cpe.emergencymanager.repository.TruckRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<FiremanEntity> getAllFireman() {
        return this.firemanRepository.findAll();
    }

    public List<TruckEntity> getAllTrucks() {
        return this.truckRepository.findAll();
    }
}
