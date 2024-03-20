package com.example.ams.service;

import com.example.ams.entities.User;
import com.example.ams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
        if(Objects.nonNull(userDetails.getName()) && !"".equalsIgnoreCase(userDetails.getName())){
            user.setName(userDetails.getName());
        }
        if(Objects.nonNull(userDetails.getEmail()) && !"".equalsIgnoreCase(userDetails.getEmail())){
            user.setEmail(userDetails.getEmail());
        }
        if(Objects.nonNull(userDetails.getApartment())){
            user.setApartment(userDetails.getApartment());
        }
        if(Objects.nonNull(userDetails.getRole())) {
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
