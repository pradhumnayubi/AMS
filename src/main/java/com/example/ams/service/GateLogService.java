package com.example.ams.service;

import com.example.ams.entities.GateLog;
import com.example.ams.entities.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface GateLogService {


//    GateLog logCheckIn(int userId, Timestamp checkInTime);
//
//    GateLog logCheckOut(int userId, Timestamp checkOutTime);
//
//    Optional<GateLog> findLatestGateLogByUserId(int userId);

    GateLog logCheckIn(User user);
    GateLog logCheckOut(User user);




    public List<GateLog> getAllGateLogs();
    public GateLog getGateLogById(int logId) throws ChangeSetPersister.NotFoundException;
    public GateLog updateGateLog(int logId, GateLog gateLogDetails) throws ChangeSetPersister.NotFoundException;
    public  void deleteGateLog(int logId) throws ChangeSetPersister.NotFoundException;

//    public User findMostActiveUser() throws ChangeSetPersister.NotFoundException;

    public User findMostActiveUser() throws ChangeSetPersister.NotFoundException;

    List<User> findSuspectUsers(Timestamp crimeStartTime, Timestamp crimeEndTime);

//    User findMostActiveUser();
}
