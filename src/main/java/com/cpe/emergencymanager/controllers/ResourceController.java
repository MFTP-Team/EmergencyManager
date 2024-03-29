package com.cpe.emergencymanager.controllers;

import com.cpe.emergencymanager.model.FiremanEntity;
import com.cpe.emergencymanager.model.StationEntity;
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

    @CrossOrigin("*")
    @GetMapping("/station/geo/point")
    private ResponseEntity<FeatureCollection> getStationGeo() {
        return ResponseEntity.ok(resourceService.getAllStationsGeo());
    }

    @CrossOrigin("*")
    @GetMapping("/truck/geo/point")
    private ResponseEntity<FeatureCollection> getTrucksGeo() {
        return ResponseEntity.ok(resourceService.getAllTrucksGeo());
    }

    @CrossOrigin("*")
    @PostMapping("/add/fireman")
    private ResponseEntity<FiremanEntity> addFireman(@RequestBody FiremanEntity firemanEntity) {
        return ResponseEntity.ok(resourceService.saveFireman(firemanEntity));
    }

    @CrossOrigin("*")
    @PostMapping("/add/truck")
    private ResponseEntity<TruckEntity> addTruck(@RequestBody TruckEntity truckEntity) {
        return ResponseEntity.ok(resourceService.saveTruck(truckEntity));
    }

    @CrossOrigin("*")
    @PostMapping("/add/station")
    private ResponseEntity<StationEntity> addStation(@RequestBody StationEntity stationEntity) {
        return ResponseEntity.ok(resourceService.saveStation(stationEntity));
    }

    @CrossOrigin("*")
    @GetMapping("/get/fireman")
    private ResponseEntity<List<FiremanEntity>> getFiremen() {
        return ResponseEntity.ok(resourceService.getAllFireman());
    }

    @CrossOrigin("*")
    @GetMapping("/get/station")
    private ResponseEntity<List<StationEntity>> getStations() {
        return ResponseEntity.ok(resourceService.getAllStations());
    }

    @CrossOrigin("*")
    @GetMapping("/get/truck")
    private ResponseEntity<List<TruckEntity>> getTrucks() {
        return ResponseEntity.ok(resourceService.getAllTrucks());
    }

    @CrossOrigin("*")
    @GetMapping("/get/fireman/{id}")
    private ResponseEntity<FiremanEntity> getFireman(@PathVariable("id") int firemanId) {
        return ResponseEntity.ok(resourceService.getFireman(firemanId));
    }

    @CrossOrigin("*")
    @GetMapping("/get/truck/{id}")
    private ResponseEntity<TruckEntity> getTruck(@PathVariable("id") int truckId) {
        return ResponseEntity.ok(resourceService.getTruck(truckId));
    }

    @CrossOrigin("*")
    @GetMapping("/get/station/{id}")
    private ResponseEntity<StationEntity> getStation(@PathVariable("id") int stationId) {
        return ResponseEntity.ok(resourceService.getStation(stationId));
    }

    @CrossOrigin("*")
    @DeleteMapping("/delete/station/{id}")
    private ResponseEntity<StationEntity> deleteStation(@PathVariable("id") int stationId) {
        resourceService.deleteStation(stationId);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin("*")
    @DeleteMapping("/delete/fireman/{id}")
    private ResponseEntity<?> deleteFireman(@PathVariable("id") int firemanId) {
        resourceService.deleteFireman(firemanId);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin("*")
    @DeleteMapping("/delete/truck/{id}")
    private ResponseEntity<?> deleteTruck(@PathVariable("id") int truckId) {
        resourceService.deleteTruck(truckId);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin("*")
    @PutMapping("/edit/fireman")
    private ResponseEntity<FiremanEntity> editFireman(@RequestBody FiremanEntity firemanEntity) {
        return ResponseEntity.ok(resourceService.saveFireman(firemanEntity));
    }

    @CrossOrigin("*")
    @PutMapping("/edit/truck")
    private ResponseEntity<TruckEntity> editTruck(@RequestBody TruckEntity truckEntity) {
        return ResponseEntity.ok(resourceService.saveTruck(truckEntity));
    }

    @CrossOrigin("*")
    @PutMapping("/edit/station")
    private ResponseEntity<StationEntity> editStation(@RequestBody StationEntity stationEntity) {
        return ResponseEntity.ok(resourceService.saveStation(stationEntity));
    }
}
