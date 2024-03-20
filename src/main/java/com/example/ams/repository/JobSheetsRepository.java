package com.example.ams.repository;

import com.example.ams.entities.JobSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSheetsRepository extends JpaRepository<JobSheet, Integer> {
}
