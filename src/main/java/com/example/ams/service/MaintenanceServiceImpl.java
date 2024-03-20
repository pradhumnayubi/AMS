package com.example.ams.service;

import com.example.ams.entities.Maintenance;
import com.example.ams.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Override
    public void handleMaintenancePayment(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
    }

    @Override
    public List<Maintenance> getAllMaintenanceDetails() {
        return maintenanceRepository.findAll();
    }

    @Override
    public Maintenance getMaintenanceDetailsById(int id) throws ChangeSetPersister.NotFoundException {
        Optional<Maintenance> optionalMaintenanceDetails = maintenanceRepository.findById(id);
        if (optionalMaintenanceDetails.isPresent()) {
            return optionalMaintenanceDetails.get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }    }

    @Override
    public Maintenance updateMaintenanceDetails(int id, Maintenance maintenance) throws ChangeSetPersister.NotFoundException {
        Maintenance existingMaintenanceDetails = getMaintenanceDetailsById(id);
        if(Objects.nonNull(maintenance.getAmount())){
            existingMaintenanceDetails.setAmount(maintenance.getAmount());
        }
        if(Objects.nonNull(maintenance.getApartment())){
            existingMaintenanceDetails.setApartment(maintenance.getApartment());
        }
        if(Objects.nonNull(maintenance.getDate())){
            existingMaintenanceDetails.setDate(maintenance.getDate());
        }
        return maintenanceRepository.save(existingMaintenanceDetails);
    }

    @Override
    public void deleteMaintenanceDetails(int id) throws ChangeSetPersister.NotFoundException {
        Maintenance maintenanceDetails = getMaintenanceDetailsById(id);
        maintenanceRepository.delete(maintenanceDetails);
    }
}
