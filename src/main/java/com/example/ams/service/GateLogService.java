package com.example.ams.service;

import com.example.ams.entities.GateLog;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface GateLogService {
    public GateLog logGateActivity(GateLog gateLog);
    public List<GateLog> getAllGateLogs();
    public GateLog getGateLogById(int logId) throws ChangeSetPersister.NotFoundException;
    public GateLog updateGateLog(int logId, GateLog gateLogDetails) throws ChangeSetPersister.NotFoundException;
    public  void deleteGateLog(int logId) throws ChangeSetPersister.NotFoundException;
}
