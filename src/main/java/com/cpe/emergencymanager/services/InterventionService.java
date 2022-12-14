package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.model.FireEntity;
import com.cpe.emergencymanager.model.InterventionEntity;
import com.cpe.emergencymanager.model.enums.ActionStatus;
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

    public void triggerIntervention(FireEntity fireEntity) {
        Boolean isInterventionTriggered = false;
        if (fireEntity.getInterventions() != null) {
            isInterventionTriggered = fireEntity.getInterventions().stream()
                    .map(interventionEntity -> interventionEntity.getStatus())
                    .anyMatch(status -> status.equals(ActionStatus.IN_PROGRESS));
        }
        if (!isInterventionTriggered) {
            InterventionEntity interventionEntity = new InterventionEntity();
            interventionEntity.setIdFire(fireEntity.getId());
            interventionEntity.setStatus(ActionStatus.IN_PROGRESS);
            this.interventionRepository.save(interventionEntity);
        }
    }

    public InterventionEntity createIntervention(FireEntity fireEntity) {
        // TODO : Faire la récupération des ressources
        // Récupérer la caserne la plus proche du feu
        // regarder si elle a des ressources disponibles
        // si oui, les assigner à l'intervention
        // si non, supprimer la caserne de la liste et recommencer jusqu'a trouver une caserne avec des ressources
        return new InterventionEntity();
    }
}
