package com.example.ams.service;

import com.example.ams.entities.Admin;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface AdminService {
    public Admin registerAdmin(Admin admin);
//    public Admin authenticateAdmin(String username, String password);
    public List<Admin> getAllAdmins();
    public Admin getAdminById(int adminId) throws ChangeSetPersister.NotFoundException;
    public Admin updateAdmin(int adminId, Admin adminDetails);
    public void deleteAdmin(int adminId) throws ChangeSetPersister.NotFoundException;
}
