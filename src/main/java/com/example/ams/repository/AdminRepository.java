package com.example.ams.repository;


import com.example.ams.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
//    Optional<Admin> findById(Integer adminId);
}
