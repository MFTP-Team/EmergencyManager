package com.cpe.emergencymanager.controllers;

import com.cpe.emergencymanager.model.FiremanEntity;
import com.cpe.emergencymanager.model.TruckEntity;
import com.cpe.emergencymanager.repository.TruckRepository;
import com.cpe.emergencymanager.services.ResourceService;
import mil.nga.sf.geojson.FeatureCollection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService, TruckRepository truckRepository) {
        this.resourceService = resourceService;
    }

    @PostMapping("/add/fireman")
    private ResponseEntity<FiremanEntity> addFireman(@RequestBody FiremanEntity firemanEntity) {
        return ResponseEntity.ok(resourceService.saveFireman(firemanEntity));
    }

    @PostMapping("/add/truck")
    private ResponseEntity<TruckEntity> addTruck(@RequestBody TruckEntity truckEntity) {
        return ResponseEntity.ok(resourceService.saveTruck(truckEntity));
    }

    @GetMapping("/get/fireman")
    private ResponseEntity<List<FiremanEntity>> getFiremen() {
        return ResponseEntity.ok(resourceService.getAllFireman());
    }

    @GetMapping("/get/truck")
    private ResponseEntity<List<TruckEntity>> getTrucks() {
        return ResponseEntity.ok(resourceService.getAllTrucks());
    }

    @GetMapping("/station/geo")
    private ResponseEntity<FeatureCollection> getStationGeo() {
        return ResponseEntity.ok(resourceService.getAllStationsGeo());
    }

    @GetMapping("/get/fireman/{id}")
    private ResponseEntity<FiremanEntity> getFireman(@PathVariable("id") int firemanId) {
        return ResponseEntity.ok(resourceService.getFireman(firemanId));
    }

    @GetMapping("/get/truck/{id}")
    private ResponseEntity<TruckEntity> getTruck(@PathVariable("id") int truckId) {
        return ResponseEntity.ok(resourceService.getTruck(truckId));
    }

    @DeleteMapping("/delete/fireman/{id}")
    private ResponseEntity<?> deleteFireman(@PathVariable("id") int firemanId) {
        resourceService.deleteFireman(firemanId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/truck/{id}")
    private ResponseEntity<?> deleteTruck(@PathVariable("id") int truckId) {
        resourceService.deleteTruck(truckId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/edit/fireman")
    private ResponseEntity<FiremanEntity> editFireman(@RequestBody FiremanEntity firemanEntity) {
        return ResponseEntity.ok(resourceService.saveFireman(firemanEntity));
    }

    @PutMapping("/edit/truck")
    private ResponseEntity<TruckEntity> editTruck(@RequestBody TruckEntity truckEntity) {
        return ResponseEntity.ok(resourceService.saveTruck(truckEntity));
    }
}
