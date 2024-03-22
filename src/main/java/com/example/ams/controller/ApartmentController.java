package com.example.ams.controller;
//
//public class ApartmentController {
//}

import com.example.ams.entities.Apartment;
import com.example.ams.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping
    public ResponseEntity<List<Apartment>> getAllApartments() {
        List<Apartment> apartments = apartmentService.getAllApartments();
        return new ResponseEntity<>(apartments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        Apartment apartment = apartmentService.getApartmentById(id);
        return new ResponseEntity<>(apartment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Apartment> createApartment(@RequestBody Apartment apartment) {
        Apartment createdApartment = apartmentService.registerApartment(apartment);
        return new ResponseEntity<>(createdApartment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apartment> updateApartment(@PathVariable("id") int id, @RequestBody Apartment apartmentDetails) throws ChangeSetPersister.NotFoundException {
        Apartment updatedApartment = apartmentService.updateApartment(id, apartmentDetails);
        return new ResponseEntity<>(updatedApartment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApartment(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        apartmentService.deleteApartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

