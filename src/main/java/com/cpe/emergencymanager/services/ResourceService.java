package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.repository.FiremanRepository;
import com.cpe.emergencymanager.repository.StationRepository;
import com.cpe.emergencymanager.repository.TruckRepository;
import org.springframework.stereotype.Service;

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
}
