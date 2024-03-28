package com.example.ams.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.ams.entities.GateLog;
import com.example.ams.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface GateLogRepository extends JpaRepository<GateLog,Integer> {

    @Query("SELECT g FROM GateLog g WHERE g.user = :user AND g.checkOutTime IS NULL ORDER BY g.checkInTime DESC")
    Optional<GateLog> findLastCheckIn(@Param("user") User user);

    @Query("SELECT g.user.userId FROM GateLog g GROUP BY g.user.userId ORDER BY COUNT(g.user.userId) DESC")
    Page<Integer> findMostActiveUserId(Pageable pageable);

//    @Query("SELECT DISTINCT g.user FROM GateLog g " +
//            "WHERE g.checkInTime <= :checkInEndTime " +
//            "AND (g.checkOutTime IS NULL OR g.checkOutTime >= :checkOutStartTime)")
//    List<User> findSuspectUsers(@Param("checkInEndTime") Timestamp checkInEndTime,
//                                @Param("checkOutStartTime") Timestamp checkOutStartTime);

    @Query("SELECT DISTINCT g.user.userId FROM GateLog g " +
            "WHERE g.checkInTime <= :crimeEndTime " +
            "AND (g.checkOutTime IS NULL OR g.checkOutTime >= :crimeStartTime)")
    List<Integer> findSuspectUserIds(@Param("crimeStartTime") Timestamp crimeStartTime,
                                     @Param("crimeEndTime") Timestamp crimeEndTime);


//    @Query("SELECT g.user.userId FROM GateLog g GROUP BY g.user.userId ORDER BY COUNT(g) DESC")
//    List<Integer> findMostActiveUserIds(Pageable pageable);


//    @Query(nativeQuery = true,value = "SELECT g FROM GateLog g WHERE g.user = :user AND g.checkInTime IS NOT NULL ORDER BY g.checkInTime DESC LIMIT 1")
//    Optional<GateLog> findLastCheckOut(@Param("user") User user);


}