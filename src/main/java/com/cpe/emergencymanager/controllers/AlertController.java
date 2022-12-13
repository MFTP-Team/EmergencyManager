package com.cpe.emergencymanager.controllers;

import com.cpe.emergencymanager.model.AlertEntity;
import com.cpe.emergencymanager.services.AlertService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alert")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping("/add")
    public ResponseEntity<AlertEntity> addAlert(@RequestBody AlertEntity alertEntity) {
        return ResponseEntity.ok(alertService.addAlert(alertEntity));
    }

    @PutMapping("/edit")
    public ResponseEntity<AlertEntity> editAlert(@RequestBody AlertEntity alertEntity) {
        return ResponseEntity.ok(alertService.editAlert(alertEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAlert(@PathVariable("id") int alertId) {
        alertService.deleteAlert(alertId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<AlertEntity>> getAlert() {
        return ResponseEntity.ok(alertService.getAlerts());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AlertEntity> getAlert(@PathVariable("id") int alertId) {
        return ResponseEntity.ok(alertService.getAlert(alertId));
    }

    @PostMapping
    public void receiveAlert(@RequestBody AlertEntity alertEntity) {
        alertService.receiveAlert(alertEntity);
    }
}
