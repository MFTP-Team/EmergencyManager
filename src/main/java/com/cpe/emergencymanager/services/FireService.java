package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.repository.FireRepository;
import org.springframework.stereotype.Service;

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
}
