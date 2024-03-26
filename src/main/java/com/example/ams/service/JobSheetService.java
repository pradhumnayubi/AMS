package com.example.ams.service;

import com.example.ams.entities.JobSheet;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSheetService {
    public List<JobSheet> getAllJobSheets();
    public JobSheet getJobSheetById(int id) throws ChangeSetPersister.NotFoundException;

//    List<JobSheet> getAllJobSheetsByApartmentId(int apartmentId);

    public JobSheet createJobSheet(JobSheet jobSheet);
    public JobSheet updateJobSheet(int id, JobSheet jobSheetDetails) throws ChangeSetPersister.NotFoundException;
    public void deleteJobSheet(int id) throws ChangeSetPersister.NotFoundException;

}
