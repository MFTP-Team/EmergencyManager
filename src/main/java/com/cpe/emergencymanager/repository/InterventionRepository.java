package com.cpe.emergencymanager.repository;

import com.cpe.emergencymanager.model.InterventionEntity;
import com.cpe.emergencymanager.model.enums.ActionStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Classe Repository permettant d'accéder aux objets Intervention en bases
 */
public interface InterventionRepository extends Repository<InterventionEntity, Integer> {
    InterventionEntity findById(Integer id);
    List<InterventionEntity> findByStatus(ActionStatus status);
    InterventionEntity save(InterventionEntity interventionEntity);
    InterventionEntity deleteById(Integer id);

    List<InterventionEntity> findAll();

    @Modifying
    @Query(value = "insert into truck_intervention values (:truckId, :interventionId)")
    @Transactional
    void linkTruckToIntervention(Integer interventionId, Integer truckId);
}
