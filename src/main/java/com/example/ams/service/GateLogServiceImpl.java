package com.example.ams.service;

import com.example.ams.entities.GateLog;
import com.example.ams.repository.GateLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GateLogServiceImpl implements GateLogService {

    @Autowired
    private GateLogRepository gateLogRepository;

    @Override
    public GateLog logGateActivity(GateLog gateLog) {
        return gateLogRepository.save(gateLog);
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
        if(Objects.nonNull(gateLogDetails.getApartment())) {
            gateLog.setApartment(gateLogDetails.getApartment());
        }

        return gateLogRepository.save(gateLog);
    }

    @Override
    public void deleteGateLog(int logId) throws ChangeSetPersister.NotFoundException {
        GateLog gateLog = getGateLogById(logId);
        gateLogRepository.delete(gateLog);
    }
}
