package com.cpe.emergencymanager.repository;

import com.cpe.emergencymanager.model.AlertEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Classe Repository permettant d'accéder aux objets Alert en bases
 */
public interface AlertRepository extends Repository<AlertEntity, Integer> {
    AlertEntity findById(Integer id);
    AlertEntity save(AlertEntity alertEntity);
    public void delete(AlertEntity alertEntity);
    AlertEntity deleteById(Integer id);
    List<AlertEntity> findAll();
}