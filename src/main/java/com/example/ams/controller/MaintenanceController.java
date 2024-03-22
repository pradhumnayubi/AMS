package com.example.ams.controller;

import com.example.ams.entities.Maintenance;
import com.example.ams.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/maintenances")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllMaintenanceDetails() {
        List<Maintenance> maintenanceDetailsList = maintenanceService.getAllMaintenanceDetails();
        return new ResponseEntity<>(maintenanceDetailsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceDetailsById(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        Maintenance maintenanceDetails = maintenanceService.getMaintenanceDetailsById(id);
        return new ResponseEntity<>(maintenanceDetails, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Maintenance> handleMaintenancePayment(@RequestBody Maintenance maintenanceDetails) {
        maintenanceService.handleMaintenancePayment(maintenanceDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenanceDetails(@PathVariable("id") int id, @RequestBody Maintenance maintenanceDetails) throws ChangeSetPersister.NotFoundException {
        Maintenance updatedMaintenanceDetails = maintenanceService.updateMaintenanceDetails(id, maintenanceDetails);
        return new ResponseEntity<>(updatedMaintenanceDetails, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenanceDetails(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        maintenanceService.deleteMaintenanceDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

