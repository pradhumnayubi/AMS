package com.example.ams.service;

import com.example.ams.entities.Apartment;
import com.example.ams.repository.ApartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ApartmentServiceImpl implements ApartmentService{

    @Autowired
    private ApartmentsRepository apartmentRepository;

    @Override
    public Apartment registerApartment(Apartment apartment) {

        return apartmentRepository.save(apartment);
    }


    @Override
    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment getApartmentById(int apartmentId) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(apartmentId);
        if (optionalApartment.isPresent()) {
            return optionalApartment.get();
        } else {
            throw new NotFoundException("Apartment not found with id: " + apartmentId);
        }
    }

    @Override
    public Apartment updateApartment(int apartmentId, Apartment apartmentDetails) {
        Apartment apartment = getApartmentById(apartmentId);
        apartment.setAddress(apartmentDetails.getAddress());
        apartment.setLayout(apartmentDetails.getLayout());

        return apartmentRepository.save(apartment);
    }

    @Override
    public void deleteApartment(int apartmentId) {
        Apartment apartment = getApartmentById(apartmentId);
        apartmentRepository.delete(apartment);
    }
}

