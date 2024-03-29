package com.example.ams.repository;

import com.example.ams.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
    @Query("SELECT m FROM Maintenance m WHERE m.apartment.apartmentId = :apartmentId")
    Optional<Maintenance> findByApartmentId(@Param("apartmentId") int apartmentId);
}
