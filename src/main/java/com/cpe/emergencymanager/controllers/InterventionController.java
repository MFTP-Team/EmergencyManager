package com.cpe.emergencymanager.controllers;

import com.cpe.emergencymanager.model.InterventionEntity;
import com.cpe.emergencymanager.services.InterventionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intervention")
public class InterventionController {
    private final InterventionService interventionService;

    public InterventionController(InterventionService interventionService) {
        this.interventionService = interventionService;
    }

    @PostMapping("/add")
    public ResponseEntity<InterventionEntity> addIntervention(@RequestBody InterventionEntity interventionEntity) {
        return ResponseEntity.ok(interventionService.saveIntervention(interventionEntity));
    }

    @GetMapping("/get")
    public ResponseEntity<List<InterventionEntity>> getInterventionList() {
        return ResponseEntity.ok(interventionService.getInterventions());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<InterventionEntity> getIntervention(int interventionId) {
        return ResponseEntity.ok(interventionService.getIntervention(interventionId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<InterventionEntity> deleteIntervention(int interventionId) {
        interventionService.deleteIntervention(interventionId);
         return ResponseEntity.noContent().build();
    }
}
