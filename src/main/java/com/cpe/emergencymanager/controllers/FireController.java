package com.cpe.emergencymanager.controllers;

import com.cpe.emergencymanager.model.FireEntity;
import com.cpe.emergencymanager.services.FireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fire")
public class FireController {
    private final FireService fireService;

    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    @PostMapping("/add")
    public ResponseEntity<FireEntity> addFire(@RequestBody FireEntity fireEntity) {
        return ResponseEntity.ok(fireService.addFire(fireEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFire(@PathVariable("id") int fireId) {
        fireService.deleteFire(fireId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<FireEntity>> getFireList() {
        return ResponseEntity.ok(fireService.getFires());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FireEntity> getFire(@PathVariable("id") int fireId) {
        return ResponseEntity.ok(fireService.getFire(fireId));
    }

    @PutMapping("/edit")
    public ResponseEntity<FireEntity> editFire(@RequestBody FireEntity fireEntity) {
        return ResponseEntity.ok(fireService.editFire(fireEntity));
    }
}
