package com.example.ams.service;

import com.example.ams.entities.Apartment;

import java.util.List;

public interface ApartmentService {
    public Apartment registerApartment(Apartment apartment);
    public List<Apartment> getAllApartments();
    public  Apartment getApartmentById(int apartmentId);
    public Apartment updateApartment(int apartmentId, Apartment apartmentDetails);
    public void deleteApartment(int apartmentId);
}
