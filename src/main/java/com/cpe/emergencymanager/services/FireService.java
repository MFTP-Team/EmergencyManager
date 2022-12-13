package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.model.FireEntity;
import com.cpe.emergencymanager.repository.FireRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireService {
    private final ResourceService resourceService;
    private final FireRepository fireRepository;
    private final InterventionService interventionService;

    public FireService(ResourceService resourceService, FireRepository fireRepository, InterventionService interventionService) {
        this.resourceService = resourceService;
        this.fireRepository = fireRepository;
        this.interventionService = interventionService;
    }

    public FireEntity addFire(FireEntity fireEntity) {
        return this.fireRepository.save(fireEntity);
    }

    public void deleteFire(int fireId) {
        this.fireRepository.deleteById(fireId);
    }

    public FireEntity getFire(int fireId) {
        return this.fireRepository.findById(fireId);
    }

    public FireEntity editFire(FireEntity fireEntity) {
        return this.fireRepository.save(fireEntity);
    }

    public List<FireEntity> getFires() {
        return this.fireRepository.findAll();
    }
}
