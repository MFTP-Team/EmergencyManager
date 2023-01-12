package com.cpe.emergencymanager.repository;

import com.cpe.emergencymanager.model.InterventionEntity;
import com.cpe.emergencymanager.model.enums.ActionStatus;
import org.springframework.data.repository.Repository;

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
}
