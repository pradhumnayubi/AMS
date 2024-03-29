package com.example.ams.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.ams.entities.GateLog;
import com.example.ams.entities.User;
import com.example.ams.repository.GateLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GateLogServiceImpl implements GateLogService {

    @Autowired
    private GateLogRepository gateLogRepository;

    @Autowired
    private UserService userService;

//    @Override
//    public GateLog logGateActivity(GateLog gateLog) {
//        return gateLogRepository.save(gateLog);
//    }
//    @Override
//    public GateLog logGateActivity(GateLog gateLog, boolean isCheckIn) throws IllegalStateException {
//        int userId = gateLog.getUser().getUserId();// Get the user ID from the GateLog's associated User object
//
//        if (gateLogRepository.count() == 0) {
//            // Create the first gate log directly without validation
//            if (isCheckIn) {
//                gateLog.setCheckInTime(new Timestamp(System.currentTimeMillis()));
//            } else {
//                gateLog.setCheckOutTime(new Timestamp(System.currentTimeMillis()));
//            }
//            return gateLogRepository.save(gateLog);
//            }
//
//        if (isCheckIn) {
//            validateCheckIn(userId);
//            gateLog.setCheckInTime(new Timestamp(System.currentTimeMillis()));
//        } else {
//            validateCheckOut(userId);
//            gateLog.setCheckOutTime(new Timestamp(System.currentTimeMillis()));
//        }
//        return gateLogRepository.save(gateLog);
//

    @Override
    public GateLog logCheckIn(User user) {
        //Check if the user has already checked in
        if (gateLogRepository.findLastCheckIn(user).isPresent()) {
            throw new IllegalStateException("User has already checked in");
        }

        // Create a new gate log entry for check-in
        GateLog gateLog = new GateLog();
        gateLog.setUser(user);
        gateLog.setCheckInTime(Timestamp.valueOf(LocalDateTime.now()));
        return gateLogRepository.save(gateLog);
    }

    @Override
    public GateLog logCheckOut(User user) {
        // Check if the user has checked in before
        Optional<GateLog> lastCheckIn = gateLogRepository.findLastCheckIn(user);
        if (lastCheckIn.isEmpty()) {
            throw new IllegalStateException("User must check in before checking out");
        }

        // Check if the user has already checked out
//        Optional<GateLog> lastCheckOut = gateLogRepository.findLastCheckOut(user);
//        if (lastCheckOut.isPresent()) {
//            throw new IllegalStateException("User has already checked out");
//        }

        // Update the check-out time of the last check-in entry
        GateLog checkInEntry = lastCheckIn.get();
        checkInEntry.setCheckOutTime(Timestamp.valueOf(LocalDateTime.now()));
        return gateLogRepository.save(checkInEntry);
    }


    @Override
    public List<GateLog> getAllGateLogs() {
        return gateLogRepository.findAll();
    }

    @Override
    public GateLog getGateLogById(int logId) throws ChangeSetPersister.NotFoundException {
        Optional<GateLog> optionalGateLog = gateLogRepository.findById(logId);
        if (optionalGateLog.isPresent()) {
            return optionalGateLog.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }    }

    @Override
    public GateLog updateGateLog(int logId, GateLog gateLogDetails) throws ChangeSetPersister.NotFoundException {
        GateLog gateLog = getGateLogById(logId);
        if(Objects.nonNull(gateLogDetails.getCheckInTime())){
            gateLog.setCheckInTime(gateLogDetails.getCheckInTime());
        }
        if(Objects.nonNull(gateLogDetails.getCheckOutTime())) {
            gateLog.setCheckOutTime(gateLogDetails.getCheckOutTime());
        }
        if(Objects.nonNull(gateLogDetails.getUser())) {
            gateLog.setUser(gateLogDetails.getUser());
        }
//        if(Objects.nonNull(gateLogDetails.getApartment())) {
//            gateLog.setApartment(gateLogDetails.getApartment());
//        }

        return gateLogRepository.save(gateLog);
    }

    @Override
    public void deleteGateLog(int logId) throws ChangeSetPersister.NotFoundException {
        GateLog gateLog = getGateLogById(logId);
        gateLogRepository.delete(gateLog);
    }

//    @Override
//    public User findMostActiveUser() throws ChangeSetPersister.NotFoundException {
//        // Find the user ID with the most gate logs
////        Integer mostActiveUserId = gateLogRepository.findMostActiveUserId();
////        if (mostActiveUserId == null) {
////            throw new ChangeSetPersister.NotFoundException();
////        }
////        // Retrieve the User object based on the most active user ID
////        return userService.getUserById(mostActiveUserId);
//    }

    @Override
    public User findMostActiveUser() throws ChangeSetPersister.NotFoundException {
        // Call the repository method to find the user IDs with the most gate logs
        Page<Integer> mostActiveUserIds = gateLogRepository.findMostActiveUserId(PageRequest.of(0,1));
        if (mostActiveUserIds == null) {
            throw new ChangeSetPersister.NotFoundException();
        }

        Integer mostActiveUserId = mostActiveUserIds.getContent().get(0);
        return userService.getUserById(mostActiveUserId);
    }

    @Override
    public List<User> findSuspectUsers(Timestamp crimeStartTime, Timestamp crimeEndTime) {
        // Find the list of suspect users based on the specified time range
        List<Integer> suspectUserIds = gateLogRepository.findSuspectUserIds(crimeStartTime, crimeEndTime);

        // Convert user IDs to users
        List<User> suspectUsers = userService.findUsersByIds(suspectUserIds);

        return suspectUsers;
    }


}