package com.example.ams.service;

import com.example.ams.entities.Apartment;
import com.example.ams.entities.User;
import com.example.ams.repository.ApartmentRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class ApartmentServiceImpl implements ApartmentService{

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Override
    public Apartment registerApartment(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }


    @Override
    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment getApartmentById(int apartmentId) throws ChangeSetPersister.NotFoundException {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(apartmentId);
        if (optionalApartment.isPresent()) {
            return optionalApartment.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public Apartment updateApartment(int apartmentId, Apartment apartmentDetails) throws ChangeSetPersister.NotFoundException {
        Apartment apartment = getApartmentById(apartmentId);
        if(Objects.nonNull(apartmentDetails.getAddress()) && !"".equalsIgnoreCase(apartmentDetails.getAddress())){
            apartment.setAddress(apartmentDetails.getAddress());
        }
        if(Objects.nonNull(apartmentDetails.getLayout())){
            apartment.setLayout(apartmentDetails.getLayout());
        }
        return apartmentRepository.save(apartment);
    }

    @Override
    public void deleteApartment(int apartmentId) throws ChangeSetPersister.NotFoundException {
        Apartment apartment = getApartmentById(apartmentId);
        apartmentRepository.delete(apartment);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<User> getUsersByApartmentId(int apartmentId) {
//        // Retrieve the apartment entity by its ID
//        Apartment apartment = apartmentRepository.findById(apartmentId)
//                .orElseThrow(() -> new EntityNotFoundException("Apartment not found with id: " + apartmentId));
//
//        // Return the list of users associated with the apartment
//        return apartment.getUsers();
//    }

}

