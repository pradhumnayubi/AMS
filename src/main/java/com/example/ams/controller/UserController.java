package com.example.ams.controller;

import com.example.ams.entities.Apartment;
import com.example.ams.entities.Users;
import com.example.ams.service.ApartmentService;
import com.example.ams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentService apartmentService;
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> user = userService.getAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        Users user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // User cannot create another user, so we omit the create method

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        // Check if the apartment in the user object is already saved or not
        if (user.getApartment() != null && user.getApartment().getApartmentId() == null) {
            // If apartment is not saved, save it first
            Apartment savedApartment = apartmentService.registerApartment(user.getApartment());
            // Set the saved apartment to the user object
            user.setApartment(savedApartment);
        }

        // Create the user
        Users createdUser = userService.registerUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable("id") int id, @RequestBody Users userDetails) throws ChangeSetPersister.NotFoundException {
        Users updatedUser = userService.updateUser(id, userDetails);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/registerApartment")
    public ResponseEntity<Apartment> createApartment(@RequestBody Apartment apartment){
        Apartment createdApartment = apartmentService.registerApartment(apartment);
        return new ResponseEntity<>(createdApartment, HttpStatus.CREATED);

    }
}
