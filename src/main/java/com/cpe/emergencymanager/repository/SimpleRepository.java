package com.cpe.emergencymanager.repository;

import com.cpe.emergencymanager.model.SimpleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface SimpleRepository extends Repository<SimpleEntity, Long> {
    List<SimpleEntity> findAll();
    List<SimpleEntity> findByName(String name);

    @Query("SELECT e FROM SimpleEntity e WHERE e.name = ?1")
    List<SimpleEntity> customQueryFindExemple(String name);
}
