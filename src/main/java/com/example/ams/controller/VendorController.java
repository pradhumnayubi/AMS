package com.example.ams.controller;

import com.example.ams.entities.Vendor;
import com.example.ams.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        Vendor vendor = vendorService.getVendorById(id);
        return new ResponseEntity<>(vendor, HttpStatus.OK);
    }

    @GetMapping("/apartment/{apartmentId}")
    public ResponseEntity<List<Vendor>> getVendorsByApartmentId(@PathVariable int apartmentId) {
        List<Vendor> vendors = vendorService.getVendorsByApartmentId(apartmentId);
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        Vendor createdVendor = vendorService.registerVendor(vendor);
        return new ResponseEntity<>(createdVendor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable("id") int id, @RequestBody Vendor vendorDetails) throws ChangeSetPersister.NotFoundException {
        Vendor updatedVendor = vendorService.updateVendor(id, vendorDetails);
        return new ResponseEntity<>(updatedVendor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        vendorService.deleteVendor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

