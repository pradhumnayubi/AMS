package com.example.ams.service;

import com.example.ams.entities.AmenityRequest;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface AmenityRequestService {
    public AmenityRequest raiseRequest(AmenityRequest request);
    public List<AmenityRequest> getAllRequests();
    public AmenityRequest getRequestById(int requestId) throws ChangeSetPersister.NotFoundException;
    public AmenityRequest updateRequest(int requestId, AmenityRequest requestDetails) throws ChangeSetPersister.NotFoundException;
    public void deleteRequest(int requestId) throws ChangeSetPersister.NotFoundException;
}
