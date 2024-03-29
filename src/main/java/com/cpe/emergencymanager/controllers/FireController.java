package com.cpe.emergencymanager.controllers;

import com.cpe.emergencymanager.model.FireEntity;
import com.cpe.emergencymanager.services.FireService;
import mil.nga.sf.geojson.FeatureCollection;
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

    public ResponseEntity<FireEntity> endFire(int fireId) {
        return ResponseEntity.ok(fireService.endFire(fireId));
    }

    @CrossOrigin("*")
    @PostMapping("/add")
    public ResponseEntity<FireEntity> addFire(@RequestBody FireEntity fireEntity) {
        return ResponseEntity.ok(fireService.addFire(fireEntity));
    }

    @CrossOrigin("*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFire(@PathVariable("id") int fireId) {
        fireService.deleteFire(fireId);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin("*")
    @GetMapping("/get")
    public ResponseEntity<List<FireEntity>> getFireList() {
        return ResponseEntity.ok(fireService.getFires());
    }

    @CrossOrigin("*")
    @GetMapping("/get/{id}")
    public ResponseEntity<FireEntity> getFire(@PathVariable("id") int fireId) {
        return ResponseEntity.ok(fireService.getFire(fireId));
    }

    @CrossOrigin("*")
    @PutMapping("/edit")
    public ResponseEntity<FireEntity> editFire(@RequestBody FireEntity fireEntity) {
        return ResponseEntity.ok(fireService.editFire(fireEntity));
    }

    
    @CrossOrigin("*")
    @GetMapping("/geo/point")
    private ResponseEntity<FeatureCollection> getFireGeoPoints() {
        return ResponseEntity.ok(fireService.getAllFireGeoPoints());
    }

    @CrossOrigin("*")
    @GetMapping("/geo/polygon")
    private ResponseEntity<FeatureCollection> getStationGeoPolygon() {
        return ResponseEntity.ok(fireService.getAllFiresGeoPolygon());
    }

}

