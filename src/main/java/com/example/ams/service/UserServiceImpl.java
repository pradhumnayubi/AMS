package com.example.ams.service;

import com.example.ams.entities.Apartment;
import com.example.ams.entities.User;
import com.example.ams.repository.ApartmentRepository;
import com.example.ams.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;



    @Override
    @Transactional
    public User registerUser(User user) {
        if (user.getApartment() != null) {
            Apartment apartment = user.getApartment();
            apartment = apartmentRepository.save(apartment);
            user.setApartment(apartment);
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) throws ChangeSetPersister.NotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public User updateUser(int userId, User userDetails) throws ChangeSetPersister.NotFoundException {
        User user = getUserById(userId);

        if (Objects.nonNull(userDetails.getName()) && !userDetails.getName().isEmpty()) {
            user.setName(userDetails.getName());
        }
        if (Objects.nonNull(userDetails.getEmail()) && !userDetails.getEmail().isEmpty()) {
            user.setEmail(userDetails.getEmail());
        }
        if (Objects.nonNull(userDetails.getApartment()) && userDetails.getApartment().getApartmentId() != null) {
            Apartment apartment = apartmentRepository.findById(userDetails.getApartment().getApartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Apartment not found with id: " + userDetails.getApartment().getApartmentId()));
            user.setApartment(apartment);
        }
        if (Objects.nonNull(userDetails.getRole())){
            user.setRole(userDetails.getRole());
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int userId) throws ChangeSetPersister.NotFoundException {
        User user = getUserById(userId);
        userRepository.delete(user);
    }
}
