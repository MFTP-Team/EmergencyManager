package com.cpe.emergencymanager.repository;

import com.cpe.emergencymanager.model.StationEntity;
import com.cpe.emergencymanager.model.TruckEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface StationRepository extends Repository<StationEntity, Integer> {
    StationEntity findById(Integer id);
    StationEntity save(StationEntity truckEntity);
    StationEntity deleteById(Integer id);
    List<StationEntity> findAll();
}
