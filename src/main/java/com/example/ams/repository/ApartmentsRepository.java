package com.example.ams.repository;

import com.example.ams.entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentsRepository extends JpaRepository<Apartment, Integer> {
}
