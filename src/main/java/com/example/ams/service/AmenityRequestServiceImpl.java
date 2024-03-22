package com.example.ams.service;

import com.example.ams.entities.AmenityRequest;
import com.example.ams.repository.AmenityRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AmenityRequestServiceImpl implements AmenityRequestService{

    @Autowired
    private AmenityRequestRepository amenityRequestRepository;

    @Override
    public AmenityRequest raiseRequest(AmenityRequest request) {
        return amenityRequestRepository.save(request);
    }

    @Override
    public List<AmenityRequest> getAllRequests() {
        return amenityRequestRepository.findAll();
    }

    @Override
    public AmenityRequest getRequestById(int requestId) throws ChangeSetPersister.NotFoundException {
        Optional<AmenityRequest> optionalRequest = amenityRequestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            return optionalRequest.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public AmenityRequest updateRequest(int requestId, AmenityRequest requestDetails) throws ChangeSetPersister.NotFoundException {
        AmenityRequest request = getRequestById(requestId);
        if(Objects.nonNull(requestDetails) && !"".equalsIgnoreCase(requestDetails.getRequestType())) {
            request.setRequestType(requestDetails.getRequestType());
        }
        if(Objects.nonNull(requestDetails.getUser())){
            request.setUser(requestDetails.getUser());
        }
        // Update other fields as needed
        return amenityRequestRepository.save(request);
    }

    @Override
    public void deleteRequest(int requestId) throws ChangeSetPersister.NotFoundException {
        AmenityRequest request = getRequestById(requestId);
        amenityRequestRepository.delete(request);

    }
}
