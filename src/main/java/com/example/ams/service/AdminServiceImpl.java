package com.example.ams.service;

import com.example.ams.entities.Admin;
import com.example.ams.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Admin registerAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

//    @Override
//    public Admin authenticateAdmin(String username, String password) {
//        return adminRepository.findByUsernameAndPassword(username, password);
//    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(int adminId) throws ChangeSetPersister.NotFoundException {
        return adminRepository.findById(adminId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public Admin updateAdmin(int adminId, Admin adminDetails) {
        Admin admin = adminRepository.findById(adminId).get();
//
        if(Objects.nonNull(adminDetails.getUsername()) && !"".equalsIgnoreCase(adminDetails.getUsername())){
            admin.setUsername(adminDetails.getUsername());
        }
        if(Objects.nonNull(adminDetails.getPassword()) && !"".equalsIgnoreCase(adminDetails.getPassword())){
            admin.setPassword(adminDetails.getPassword());
        }
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(int adminId) throws ChangeSetPersister.NotFoundException {
        Admin admin = getAdminById(adminId);
        adminRepository.delete(admin);
    }
}
