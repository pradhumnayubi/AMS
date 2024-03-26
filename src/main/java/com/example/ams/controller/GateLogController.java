package com.example.ams.controller;

import com.example.ams.entities.GateLog;
import com.example.ams.entities.User;
import com.example.ams.service.GateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

//    @PostMapping("/check-in/{userId}")
//    public ResponseEntity<GateLog> logCheckIn(@PathVariable int userId) {
//        GateLog gateLog = gateLogService.logCheckIn(userId);
//        return new ResponseEntity<>(gateLog, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/check-out/{userId}")
//    public ResponseEntity<GateLog> logCheckOut(@PathVariable int userId) {
//        GateLog gateLog = gateLogService.logCheckOut(userId);
//        return ResponseEntity.ok(gateLog);
//    }

    @PostMapping("/check-in")
    public ResponseEntity<GateLog> logCheckIn(@RequestBody User user) {
        GateLog gateLog = gateLogService.logCheckIn(user);
        return new ResponseEntity<>(gateLog, HttpStatus.CREATED);
    }

    @PostMapping("/check-out")
    public ResponseEntity<GateLog> logCheckOut(@RequestBody User user) {
        GateLog gateLog = gateLogService.logCheckOut(user);
        return new ResponseEntity<>(gateLog, HttpStatus.CREATED);
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