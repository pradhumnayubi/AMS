package com.example.ams.repository;

import com.example.ams.entities.AmenityRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRequestRepository extends JpaRepository<AmenityRequest,Integer> {
}
