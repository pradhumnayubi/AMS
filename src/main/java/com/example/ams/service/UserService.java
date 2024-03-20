package com.example.ams.service;

import com.example.ams.entities.Users;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UserService {
    public Users registerUser(Users user);

    public List<Users> getAllUsers();
    public Users getUserById(int userId) throws ChangeSetPersister.NotFoundException;
    public Users updateUser(int userId, Users userDetails) throws ChangeSetPersister.NotFoundException;
    public void deleteUser(int userId) throws ChangeSetPersister.NotFoundException;
}
