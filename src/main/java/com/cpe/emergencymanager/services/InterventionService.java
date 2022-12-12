package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.repository.InterventionRepository;
import org.springframework.stereotype.Service;

@Service
public class InterventionService {
    private final ResourceService resourceService;
    private final InterventionRepository interventionRepository;

    public InterventionService(ResourceService resourceService, InterventionRepository interventionRepository) {
        this.resourceService = resourceService;
        this.interventionRepository = interventionRepository;
    }
}
