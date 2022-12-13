package com.cpe.emergencymanager.repository;

import com.cpe.emergencymanager.model.FiremanEntity;
import com.cpe.emergencymanager.model.TruckEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Classe Repository permettant d'accéder aux objets Truck en bases
 */
public interface TruckRepository extends Repository<FiremanEntity, Integer> {
    TruckEntity findById(Integer id);
    TruckEntity save(TruckEntity truckEntity);
    TruckEntity deleteById(Integer id);
    List<TruckEntity> findAll();
}
