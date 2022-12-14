package com.cpe.emergencymanager.repository;

import com.cpe.emergencymanager.model.StationEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface StationRepository extends Repository<StationEntity, Integer> {
    List<StationEntity> findAll();
}
