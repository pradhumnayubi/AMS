package com.example.ams.repository;

import com.example.ams.entities.GateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateLogRepository extends JpaRepository<GateLog,Integer> {
}
