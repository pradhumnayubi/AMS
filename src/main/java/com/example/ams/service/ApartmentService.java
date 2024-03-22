package com.example.ams.service;

import com.example.ams.entities.Apartment;
import com.example.ams.entities.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApartmentService {
    public Apartment registerApartment(Apartment apartment);
    public List<Apartment> getAllApartments();
    public  Apartment getApartmentById(int apartmentId) throws ChangeSetPersister.NotFoundException;
    public Apartment updateApartment(int apartmentId, Apartment apartmentDetails) throws ChangeSetPersister.NotFoundException;
    public void deleteApartment(int apartmentId) throws ChangeSetPersister.NotFoundException;

//    @Transactional(readOnly = true)
//    List<User> getUsersByApartmentId(int apartmentId);
}
