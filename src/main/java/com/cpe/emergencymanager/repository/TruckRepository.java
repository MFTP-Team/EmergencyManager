package com.cpe.emergencymanager.repository;

import com.cpe.emergencymanager.model.FiremanEntity;
import com.cpe.emergencymanager.model.TruckEntity;
import org.springframework.data.repository.Repository;

public interface TruckRepository extends Repository<FiremanEntity, Integer> {
}
