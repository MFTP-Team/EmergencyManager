package com.cpe.emergencymanager.repository;

import com.cpe.emergencymanager.model.FiremanEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface FiremanRepository extends Repository<FiremanEntity, Integer> {
    FiremanEntity findById(Integer id);

    List<FiremanEntity> findAll();
}
