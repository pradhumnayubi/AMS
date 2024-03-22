package com.example.ams.service;

import com.example.ams.entities.JobSheet;
import com.example.ams.repository.JobSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JobSheetServiceImpl implements JobSheetService{

    @Autowired
    private JobSheetRepository jobSheetRepository;

    @Override
    public List<JobSheet> getAllJobSheets() {
        return jobSheetRepository.findAll();
    }

    @Override
    public JobSheet getJobSheetById(int id) throws ChangeSetPersister.NotFoundException {
        Optional<JobSheet> jobSheetOptional = jobSheetRepository.findById(id);
        if (jobSheetOptional.isPresent()) {
            return jobSheetOptional.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public JobSheet createJobSheet(JobSheet jobSheet) {
        return jobSheetRepository.save(jobSheet);
    }

    @Override
    public JobSheet updateJobSheet(int id, JobSheet jobSheetDetails) throws ChangeSetPersister.NotFoundException {
        JobSheet jobSheet = getJobSheetById(id);
        if(Objects.nonNull(jobSheetDetails.getServiceId()) && id > 0) {
            jobSheet.setServiceId(jobSheetDetails.getServiceId());
        }
        if(Objects.nonNull(jobSheetDetails.getVendor())){
            jobSheet.setVendor(jobSheetDetails.getVendor());
        }
        if(Objects.nonNull(jobSheetDetails.getAmenityRequest())){
            jobSheet.setAmenityRequest(jobSheetDetails.getAmenityRequest());
        }
        if(Objects.nonNull(jobSheetDetails.getStatus())){
            jobSheet.setStatus(jobSheetDetails.getStatus());
        }
        if(Objects.nonNull(jobSheetDetails.getETA())){
            jobSheet.setETA(jobSheetDetails.getETA());
        }

        return jobSheetRepository.save(jobSheet);
    }

    @Override
    public void deleteJobSheet(int id) throws ChangeSetPersister.NotFoundException {
        JobSheet jobSheet = getJobSheetById(id);
        jobSheetRepository.delete(jobSheet);
    }
}
