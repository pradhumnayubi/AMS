package com.example.ams.service;

import com.example.ams.entities.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public List<User> getAllUsers();
    public User getUserById(int userId) throws ChangeSetPersister.NotFoundException;
    public User updateUser(int userId, User userDetails) throws ChangeSetPersister.NotFoundException;
    public void deleteUser(int userId) throws ChangeSetPersister.NotFoundException;
}
