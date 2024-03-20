package com.example.ams.service;

import com.example.ams.entities.Users;
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
    public Users registerUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(int userId) throws ChangeSetPersister.NotFoundException {
        Optional<Users> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public Users updateUser(int userId, Users userDetails) throws ChangeSetPersister.NotFoundException {
        Users user = getUserById(userId);
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
        Users user = getUserById(userId);
        userRepository.delete(user);
    }
}
