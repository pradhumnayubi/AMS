package com.example.ams.repository;

import com.example.ams.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    List<Vendor> findByApartmentApartmentId(int apartmentId);

}
