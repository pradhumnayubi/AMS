package com.example.ams.controller;


import com.example.ams.entities.Apartment;
import com.example.ams.entities.Maintenance;
import com.example.ams.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/maintenances")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;



    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceDetailsByAId(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        Maintenance maintenanceDetails = maintenanceService.getMaintenanceDetailsById(id);
        return new ResponseEntity<>(maintenanceDetails, HttpStatus.OK);
    }

    @GetMapping("/apartment/{apartmentId}")
    public ResponseEntity<List<Maintenance>> getMaintenanceByApartmentId(@PathVariable("apartmentId") int apartmentId) {
        List<Maintenance> maintenanceList = maintenanceService.getMaintenanceDetailsByApartmentId(apartmentId);
        return new ResponseEntity<>(maintenanceList, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenanceDetails(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        maintenanceService.deleteMaintenanceDetails(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/payment")
    public ResponseEntity<String> makePayment(@RequestParam("maintenanceId") int maintenanceId, @RequestParam("amountPaid") Double amountPaid) throws ChangeSetPersister.NotFoundException {
        Maintenance maintenance = maintenanceService.getMaintenanceDetailsById(maintenanceId);
        if (maintenance == null) {
            return new ResponseEntity<>("Maintenance record not found", HttpStatus.NOT_FOUND);
        }

        maintenanceService.updateMaintenancePayment(maintenanceId, amountPaid);
        return new ResponseEntity<>("Payment successful", HttpStatus.OK);
    }

    @PostMapping("/create-next-month")
    public ResponseEntity<String> createNextMonthMaintenance() {
        try {
            maintenanceService.createNextMonthMaintenance();
            return ResponseEntity.ok("Next month's maintenance records created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create next month's maintenance records: " + e.getMessage());
        }
    }

//    @GetMapping("/most-defaulted")
//    public ResponseEntity<List<Object[]>> getApartmentsWithMostDefaults() {
//        List<Object[]> results = maintenanceService.findApartmentsWithMostDefaults();
//        return new ResponseEntity<>(results, HttpStatus.OK);
//    }
@GetMapping("/most-defaults")
public ResponseEntity<List<Integer>> findApartmentsWithMostDefaults() {
    List<Integer> apartmentIds = maintenanceService.findApartmentsWithMostDefaults();
    return new ResponseEntity<>(apartmentIds, HttpStatus.OK);
}


}

