package com.example.ams.repository;

import com.example.ams.entities.JobSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSheetRepository extends JpaRepository<JobSheet, Integer> {
//    List<JobSheet> findByAmenityRequest_Apartment_ApartmentId(int apartmentId);

}
