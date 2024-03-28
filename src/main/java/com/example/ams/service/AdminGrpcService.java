package com.example.ams.service;

import com.example.ams.*;
import com.example.ams.entities.Admin;
import com.example.ams.repository.AdminRepository;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;


import javax.persistence.EntityNotFoundException;
import java.util.List;

@GrpcService
public class AdminGrpcService extends AdminServiceGrpc.AdminServiceImplBase {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void registerAdmin(AdminDetails request, StreamObserver<AdminDetails> responseObserver) {
        AdminDetails admin = AdminDetails.newBuilder()
                .setUsername(request.getUsername())
                .setPassword(request.getPassword())
                .build();
        Admin newAdmin = new com.example.ams.entities.Admin();
        newAdmin.setUsername(admin.getUsername());
        newAdmin.setPassword(admin.getPassword());
        newAdmin.setAdminId(admin.getAdminId());

        adminRepository.save(newAdmin);

        responseObserver.onNext(admin);
        responseObserver.onCompleted();

    }

    @Override
    public void getAllAdmins(Empty request, StreamObserver<AdminList> responseObserver) {
        List<Admin> admins = adminRepository.findAll();
        AdminList.Builder adminListBuilder = AdminList.newBuilder();
        admins.forEach(admin -> {
            adminListBuilder.addAdminId(admin.getAdminId());
            adminListBuilder.addUserName(admin.getUsername());
            adminListBuilder.addPassword(admin.getPassword());
        });
//        AdminList adminList = AdminList.newBuilder()
//                .addAllAdmins(admins)
//                .build();

        responseObserver.onNext(adminListBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAdminById(GetAdminByIdRequest request, StreamObserver<AdminDetails> responseObserver) {
        int adminId = request.getAdminId();
       Admin admin = adminRepository.findById(adminId)
                .orElseThrow(EntityNotFoundException::new);
       AdminDetails adminDTO = AdminDetails.newBuilder()
               .setAdminId(admin.getAdminId())
               .setUsername(admin.getUsername())
               .setPassword(admin.getPassword())
               .build();
        responseObserver.onNext(adminDTO);
        responseObserver.onCompleted();
    }

    @Override
    public void updateAdmin(UpdateAdminRequest request, StreamObserver<AdminDetails> responseObserver) {
        int adminId = request.getAdminId();

        AdminDetails adminDetailsDTO = request.getAdminDetails();
        Admin adminDB = adminRepository.findById(adminId)
                .orElseThrow(EntityNotFoundException::new);
        adminDB.setUsername(adminDetailsDTO.getUsername());
        adminDB.setPassword(adminDetailsDTO.getPassword());
        adminRepository.save(adminDB);
        responseObserver.onNext(adminDetailsDTO);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteAdmin(DeleteAdminRequest request, StreamObserver<Empty> responseObserver) {
        int adminId = request.getAdminId();
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(EntityNotFoundException::new);
        adminRepository.delete(admin);
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }



}
