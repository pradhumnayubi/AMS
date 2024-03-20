package com.example.ams.repository;

import com.example.ams.entities.MaintenanceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceDetailsRepository extends JpaRepository<MaintenanceDetails, Integer> {
}
