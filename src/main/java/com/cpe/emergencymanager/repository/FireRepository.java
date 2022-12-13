package com.cpe.emergencymanager.repository;

import com.cpe.emergencymanager.model.FireEntity;
import com.cpe.emergencymanager.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Classe Repository permettant d'accéder aux objets Fire en bases
 */
public interface FireRepository extends Repository<FireEntity, Integer> {
    FireEntity findById(Integer id);
    FireEntity save(FireEntity fireEntity);
    void delete(FireEntity fireEntity);
    FireEntity deleteById(Integer id);

    List<FireEntity> findAll();
}
