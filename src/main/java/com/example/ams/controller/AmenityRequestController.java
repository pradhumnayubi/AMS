package com.example.ams.controller;

import com.example.ams.entities.AmenityRequest;
import com.example.ams.service.AmenityRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class AmenityRequestController {

    @Autowired
    private AmenityRequestService amenityRequestService;

    @GetMapping
    public ResponseEntity<List<AmenityRequest>> getAllRequests() {
        List<AmenityRequest> requests = amenityRequestService.getAllRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmenityRequest> getRequestById(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        AmenityRequest request = amenityRequestService.getRequestById(id);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AmenityRequest>> getRequestsByUserId(@PathVariable int userId) {
        List<AmenityRequest> requests = amenityRequestService.getRequestsByUserId(userId);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AmenityRequest> createRequest(@RequestBody AmenityRequest request) {
        AmenityRequest createdRequest = amenityRequestService.raiseRequest(request);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AmenityRequest> updateRequest(@PathVariable("id") int id, @RequestBody AmenityRequest requestDetails) throws ChangeSetPersister.NotFoundException {
        AmenityRequest updatedRequest = amenityRequestService.updateRequest(id, requestDetails);
        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        amenityRequestService.deleteRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

