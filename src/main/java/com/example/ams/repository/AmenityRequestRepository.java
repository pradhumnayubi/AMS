package com.example.ams.repository;

import com.example.ams.entities.AmenityRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmenityRequestRepository extends JpaRepository<AmenityRequest,Integer> {
    List<AmenityRequest> findByUserUserId(int userId);

}
