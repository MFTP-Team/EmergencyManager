package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.model.InterventionEntity;
import com.cpe.emergencymanager.repository.InterventionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterventionService {
    private final ResourceService resourceService;
    private final InterventionRepository interventionRepository;

    public InterventionService(ResourceService resourceService, InterventionRepository interventionRepository) {
        this.resourceService = resourceService;
        this.interventionRepository = interventionRepository;
    }

    public InterventionEntity saveIntervention(InterventionEntity interventionEntity) {
        return this.interventionRepository.save(interventionEntity);
    }

    public InterventionEntity getIntervention(int interventionId) {
        return this.interventionRepository.findById(interventionId);
    }

    public void deleteIntervention(int interventionId) {
        this.interventionRepository.deleteById(interventionId);
    }

    public List<InterventionEntity> getInterventions() {
        return this.interventionRepository.findAll();
    }
}
