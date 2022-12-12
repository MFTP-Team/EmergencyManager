package com.cpe.emergencymanager.repository;

import com.cpe.emergencymanager.model.FireEntity;
import com.cpe.emergencymanager.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;

public interface FireRepository extends Repository<FireEntity, Integer> {

}
