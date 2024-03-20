package com.example.ams.service;

import com.example.ams.entities.Vendor;
import com.example.ams.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService{

    @Autowired
    private VendorRepository vendorRepository;
    @Override
    public Vendor registerVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendorById(int vendorId) throws ChangeSetPersister.NotFoundException {
        Optional<Vendor> optionalVendor = vendorRepository.findById(vendorId);
        if (optionalVendor.isPresent()) {
            return optionalVendor.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public Vendor updateVendor(int vendorId, Vendor vendorDetails) throws ChangeSetPersister.NotFoundException {
        Vendor vendor = getVendorById(vendorId);
//        if(Objects.nonNull(adminDetails.getUsername()) && !"".equalsIgnoreCase(adminDetails.getUsername())){
//            admin.setUsername(adminDetails.getUsername());
//        }
//        if(Objects.nonNull(adminDetails.getPassword()) && !"".equalsIgnoreCase(adminDetails.getPassword())){
//            admin.setPassword(adminDetails.getPassword());
//        }
        if(Objects.nonNull(vendorDetails.getName()) && !"".equalsIgnoreCase(vendorDetails.getName())){
            vendor.setName(vendorDetails.getName());
        }
        if(Objects.nonNull(vendorDetails.getType()) && !"".equalsIgnoreCase(vendorDetails.getType())){
            vendor.setType(vendorDetails.getType());
        }
        if(Objects.nonNull(vendorDetails.getApartment())){
            vendor.setApartment(vendorDetails.getApartment());
        }

        return vendorRepository.save(vendor);

    }

    @Override
    public void deleteVendor(int vendorId) throws ChangeSetPersister.NotFoundException {
        Vendor vendor = getVendorById(vendorId);
        vendorRepository.delete(vendor);
    }
}
