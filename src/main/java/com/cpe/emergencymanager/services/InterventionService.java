package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.Constantes;
import com.cpe.emergencymanager.model.FireEntity;
import com.cpe.emergencymanager.model.InterventionEntity;
import com.cpe.emergencymanager.model.StationEntity;
import com.cpe.emergencymanager.model.TruckEntity;
import com.cpe.emergencymanager.model.enums.ActionStatus;
import com.cpe.emergencymanager.model.enums.ResourceStatus;
import com.cpe.emergencymanager.repository.InterventionRepository;
import com.cpe.emergencymanager.util.CoordinateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Slf4j
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
        log.info("Triggering intervention for fire {}", fireEntity.getId());
        Boolean isInterventionTriggered = false;
        if (fireEntity.getInterventions() != null) {
            isInterventionTriggered = fireEntity.getInterventions().stream()
                    .map(interventionEntity -> interventionEntity.getStatus())
                    .anyMatch(status -> status.equals(ActionStatus.IN_PROGRESS));
            log.info("Intervention already triggered for fire {}", fireEntity.getId());
        }
        if (!isInterventionTriggered) {
            this.createIntervention(fireEntity);
            log.info("Intervention triggered for fire {}", fireEntity.getId());
        }
    }

    /**
     * Récupère la liste des caserne les plus proche du feu
     * Regarde si elle a des camions disponible
     * Créer une intervention
     * @param fireEntity
     * @return
     */
    public InterventionEntity createIntervention(FireEntity fireEntity) {
        log.info("Création d'une intervention pour le feu {}", fireEntity.getId());
        Integer fireTruckInNeed = 0;
        if(Constantes.FIRE_LEVEL_1.containsInteger(fireEntity.getIntensity())) {
            fireTruckInNeed = 2;
        } else if(Constantes.FIRE_LEVEL_2.containsInteger(fireEntity.getIntensity())) {
            fireTruckInNeed = 3;
        } else if(Constantes.FIRE_LEVEL_3.containsInteger(fireEntity.getIntensity())) {
            fireTruckInNeed = 4;
        }
        Integer finalFireTruckInNeed = fireTruckInNeed;

        // On récupère la liste des casernes qui ont assez de camions
        // uniquement à moins de 100km du feu
        // puis on la trie par distance croissante
        List<StationEntity> casernes = this.resourceService.getAllStations().stream()
                .filter(stationEntity -> stationEntity.hasEnoughAvaliableTruck(finalFireTruckInNeed))
                //.filter(stationEntity -> CoordinateUtil.distance(stationEntity.getLatitude(), stationEntity.getLongitude(), fireEntity.getLatitude(), fireEntity.getLongitude(), 'K') < 100)
                .toList();
        // Récupère la caserne la plus proche
        StationEntity caserne = casernes.get(0);

        Collection<TruckEntity> trucksInAction = caserne.getAvailableTrucks()
                .stream().limit(finalFireTruckInNeed)
                .toList();
        InterventionEntity interventionEntity = new InterventionEntity();
        interventionEntity.setIdFire(fireEntity.getId());
        interventionEntity.setStatus(ActionStatus.IN_PROGRESS);
        interventionEntity.setDate(new Timestamp(System.currentTimeMillis()));
        log.info("Intervention créée pour le feu {},{} camions en route, la caserne {} s'en charge", fireEntity.getId(),
                finalFireTruckInNeed, caserne.getId());
        interventionRepository.save(interventionEntity);
        caserne.getAvailableTrucks().stream()
                .limit(finalFireTruckInNeed)
                .forEach(fireTruckEntity -> {
                    fireTruckEntity.setStatus(ResourceStatus.UNAVAILABLE);
                    this.resourceService.saveTruck(fireTruckEntity);
                });
        interventionEntity.setTrucks(trucksInAction);
        interventionRepository.save(interventionEntity);
        return interventionEntity;
    }

    public List<InterventionEntity> getInterventionsWithStatus(ActionStatus status) {
        return this.interventionRepository.findByStatus(status);
    }
}
