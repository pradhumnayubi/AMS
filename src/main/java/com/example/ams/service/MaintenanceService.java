package com.example.ams.service;

import com.example.ams.entities.Apartment;
import com.example.ams.entities.Maintenance;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Optional;

public interface MaintenanceService {

    void createMaintenanceDetails(Maintenance maintenance);

    Maintenance getMaintenanceDetailsById(int id) throws ChangeSetPersister.NotFoundException;

    List<Maintenance> getAllMaintenanceDetails();

    List<Maintenance> getMaintenanceDetailsByApartmentId(int apartmentId);

    void deleteMaintenanceDetails(int id) throws ChangeSetPersister.NotFoundException;

    void updateMaintenancePayment(int apartmentId, Double amountPaid);

    void createNextMonthMaintenance();

//    void handleMaintenancePayment(int maintenanceId, double amountPaid);

    void createInvoice(Maintenance maintenance, Double amountPaid);

//    Optional<Apartment> findApartmentWithMostDefaults();

    List<Integer> findApartmentsWithMostDefaults();
}

