package com.cpe.emergencymanager.services;

import com.cpe.emergencymanager.dto.SimpleEntityDTO;
import com.cpe.emergencymanager.model.SimpleEntity;
import com.cpe.emergencymanager.repository.SimpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleService {

    @Autowired
    private SimpleRepository simpleRepository;

    public SimpleEntityDTO getSimpleEntityDTO(String name) {
        List<SimpleEntity> list = simpleRepository.findAll();
        return null;
    }
}
