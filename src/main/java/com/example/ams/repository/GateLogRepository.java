package com.example.ams.repository;

import com.example.ams.entities.GateLog;
import com.example.ams.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GateLogRepository extends JpaRepository<GateLog,Integer> {
//    List<GateLog> findByUser_UserIdAndCheckOutTimeIsNull(int userId);

//    Optional<GateLog> findTopByUserIdOrderByIdDesc(int userId);

//    Optional<GateLog> findTopByUserOrderByIdDesc(User user);

//    Optional<GateLog> findTopByUserIdAndCheckOutTimeIsNullOrderByIdDesc(int userId);

    @Query("SELECT g FROM GateLog g WHERE g.user = :user AND g.checkOutTime IS NULL ORDER BY g.checkInTime DESC")
    Optional<GateLog> findLastCheckIn(@Param("user") User user);

//    @Query(nativeQuery = true,value = "SELECT g FROM GateLog g WHERE g.user = :user AND g.checkInTime IS NOT NULL ORDER BY g.checkInTime DESC LIMIT 1")
//    Optional<GateLog> findLastCheckOut(@Param("user") User user);


}