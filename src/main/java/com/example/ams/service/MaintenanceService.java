package com.example.ams.service;

import com.example.ams.entities.Maintenance;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface MaintenanceService {
    public void handleMaintenancePayment(Maintenance maintenanceDetails);
    public List<Maintenance> getAllMaintenanceDetails();
    public Maintenance getMaintenanceDetailsById(int id) throws ChangeSetPersister.NotFoundException;

    List<Maintenance> getMaintenanceDetailsByApartmentId(int apartmentId);

    public Maintenance updateMaintenanceDetails(int id, Maintenance maintenance) throws ChangeSetPersister.NotFoundException;
    public void deleteMaintenanceDetails(int id) throws ChangeSetPersister.NotFoundException;
}
