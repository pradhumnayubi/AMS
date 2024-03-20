package com.example.ams.service;

import com.example.ams.entities.Vendor;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface VendorService {
    public Vendor registerVendor(Vendor vendor);
    public List<Vendor> getAllVendors();
    public Vendor getVendorById(int vendorId) throws ChangeSetPersister.NotFoundException;
    public Vendor updateVendor(int vendorId, Vendor vendorDetails) throws ChangeSetPersister.NotFoundException;
    public void deleteVendor(int vendorId) throws ChangeSetPersister.NotFoundException;

}
