package com.example.ams.controller;

import com.example.ams.entities.JobSheet;
import com.example.ams.service.JobSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/jobsheets")
public class JobSheetController {

    @Autowired
    private JobSheetService jobSheetService;

    @GetMapping
    public ResponseEntity<List<JobSheet>> getAllJobSheets() {
        List<JobSheet> jobSheets = jobSheetService.getAllJobSheets();
        return new ResponseEntity<>(jobSheets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSheet> getJobSheetById(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        JobSheet jobSheet = jobSheetService.getJobSheetById(id);
        return new ResponseEntity<>(jobSheet, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobSheet> createJobSheet(@RequestBody JobSheet jobSheet) {
        JobSheet createdJobSheet = jobSheetService.createJobSheet(jobSheet);
        return new ResponseEntity<>(createdJobSheet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSheet> updateJobSheet(@PathVariable("id") int id, @RequestBody JobSheet jobSheetDetails) throws ChangeSetPersister.NotFoundException {
        JobSheet updatedJobSheet = jobSheetService.updateJobSheet(id, jobSheetDetails);
        return new ResponseEntity<>(updatedJobSheet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobSheet(@PathVariable("id") int id) throws ChangeSetPersister.NotFoundException {
        jobSheetService.deleteJobSheet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
