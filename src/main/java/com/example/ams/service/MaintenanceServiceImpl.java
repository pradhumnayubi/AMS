package com.example.ams.service;

import com.example.ams.entities.Apartment;
import com.example.ams.entities.Maintenance;
import com.example.ams.repository.ApartmentRepository;
import com.example.ams.repository.MaintenanceRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;
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
        }
    }

    @Override
    public List<Maintenance> getMaintenanceDetailsByApartmentId(int apartmentId) {
        return maintenanceRepository.findAll().stream()
                .filter(maintenance -> maintenance.getApartment() != null && maintenance.getApartment().getApartmentId() == apartmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Maintenance updateMaintenanceDetails(int id, Maintenance maintenance) throws ChangeSetPersister.NotFoundException {
        Maintenance existingMaintenanceDetails = getMaintenanceDetailsById(id);

        if (Objects.nonNull(maintenance.getApartment())) {
            Apartment existingApartment = apartmentRepository.findById(maintenance.getApartment().getApartmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Apartment not found with id: " + maintenance.getApartment().getApartmentId()));
            existingMaintenanceDetails.setApartment(existingApartment);
        }

        if(Objects.nonNull(maintenance.getAmount())){
            existingMaintenanceDetails.setAmount(maintenance.getAmount());
        }

        if(Objects.nonNull(maintenance.getDueDate())){
            existingMaintenanceDetails.setDueDate(maintenance.getDueDate());
        }
        return maintenanceRepository.save(existingMaintenanceDetails);
    }

    @Override
    public void deleteMaintenanceDetails(int id) throws ChangeSetPersister.NotFoundException {
        Maintenance maintenanceDetails = getMaintenanceDetailsById(id);
        maintenanceRepository.delete(maintenanceDetails);
    }

    @Override
    @Scheduled(fixedRate = 30 * 24 * 60 * 60 * 1000) // Run once a month
    public void createMonthlyMaintenanceRecords() {
        List<Apartment> apartments = apartmentRepository.findAll();
        for (Apartment apartment : apartments) {
            Maintenance maintenance = new Maintenance();
            maintenance.setApartment(apartment);
            // Set default values for amount, due date, status
            maintenance.setAmount(1000.0); // Default amount
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDateTime dueDate = currentDate.plusMonths(1);
            maintenance.setDueDate(Timestamp.valueOf(dueDate)); // Due date 1 month from current date
            maintenance.setStatus(Maintenance.Status.PENDING); // Default status
            maintenanceRepository.save(maintenance);
        }
    }
}
