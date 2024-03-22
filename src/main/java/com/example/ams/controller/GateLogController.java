package com.example.ams.controller;

import com.example.ams.entities.GateLog;
import com.example.ams.service.GateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/gatelogs")
public class GateLogController {

    @Autowired
    private GateLogService gateLogService;

    @GetMapping
    public ResponseEntity<List<GateLog>> getAllGateLogs() {
        List<GateLog> gateLogs = gateLogService.getAllGateLogs();
        return new ResponseEntity<>(gateLogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GateLog> getGateLogById(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        GateLog gateLog = gateLogService.getGateLogById(id);
        return new ResponseEntity<>(gateLog, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GateLog> logGateActivity(@RequestBody GateLog gateLog) {
        GateLog loggedGateLog = gateLogService.logGateActivity(gateLog);
        return new ResponseEntity<>(loggedGateLog, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GateLog> updateGateLog(@PathVariable("id") int id, @RequestBody GateLog gateLogDetails) throws ChangeSetPersister.NotFoundException {
        GateLog updatedGateLog = gateLogService.updateGateLog(id, gateLogDetails);
        return new ResponseEntity<>(updatedGateLog, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGateLog(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        gateLogService.deleteGateLog(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
