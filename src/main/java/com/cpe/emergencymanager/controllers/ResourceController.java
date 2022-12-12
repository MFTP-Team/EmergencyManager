package com.cpe.emergencymanager.controllers;

import com.cpe.emergencymanager.model.FiremanEntity;
import com.cpe.emergencymanager.repository.FiremanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    @Autowired
    private FiremanRepository firemanRepository;

    @GetMapping("/hello")
    public Boolean pingServer() {
        return true;
    }

    @GetMapping("/fireman/{id}")
    public FiremanEntity getFiremanById(@PathVariable("id") Integer id) {
        return this.firemanRepository.findById(id);
    }

    @GetMapping("/fireman")
    public List<FiremanEntity> getAllFiremen() {
        return this.firemanRepository.findAll();
    }
}
