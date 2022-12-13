package com.cpe.emergencymanager.repository;

import com.cpe.emergencymanager.model.FiremanEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Classe Repository permettant d'accéder aux objets Fireman en bases
 */
public interface FiremanRepository extends Repository<FiremanEntity, Integer> {
    FiremanEntity findById(Integer id);
    FiremanEntity save(FiremanEntity firemanEntity);
    void deleteById(Integer id);

    List<FiremanEntity> findAll();
}
