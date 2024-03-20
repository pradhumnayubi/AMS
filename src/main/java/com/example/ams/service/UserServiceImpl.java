package com.example.ams.service;

import com.example.ams.entities.User;
import com.example.ams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
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
        user.setUserName(userDetails.getUserName());
        user.setEmail(userDetails.getEmail());
        user.setApartmentId(userDetails.getApartmentId());
        user.setRole(userDetails.getRole());
        // Update other fields as needed
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(int userId) throws ChangeSetPersister.NotFoundException {
        User user = getUserById(userId);
        userRepository.delete(user);
    }
}
