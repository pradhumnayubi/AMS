package com.example.ams.controller;

import com.example.ams.entities.Admin;
import com.example.ams.entities.Apartment;
import com.example.ams.entities.User;
import com.example.ams.service.AdminService;
import com.example.ams.service.ApartmentService;
import com.example.ams.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApartmentService apartmentService;

    @PostMapping("/register")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = adminService.registerAdmin(admin);
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }
//    @PostMapping("/admins")
//    public Admin createAdmin(@RequestBody Admin admin){
//        return adminService.registerAdmin(admin);
//    }

    @GetMapping()
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        Admin admin = adminService.getAdminById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") int id, @RequestBody Admin adminDetails) {
        Admin updatedAdmin = adminService.updateAdmin(id, adminDetails);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        adminService.deleteAdmin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user.getApartment() != null && user.getApartment().getApartmentId() == 0) {
            // If apartment is not saved, save it first
            Apartment savedApartment = apartmentService.registerApartment(user.getApartment());
            // Set the saved apartment to the user object
            user.setApartment(savedApartment);
        }

        // Create the user
        User createdUser = userService.registerUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

}
