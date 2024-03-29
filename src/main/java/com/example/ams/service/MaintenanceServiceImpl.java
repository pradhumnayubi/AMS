package com.example.ams.service;

import com.example.ams.entities.Invoice;
import com.example.ams.entities.Maintenance;
import com.example.ams.repository.ApartmentRepository;
import com.example.ams.repository.InvoiceRepository;
import com.example.ams.repository.MaintenanceRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;
    @Override
    public void createMaintenanceDetails(Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
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
    public void deleteMaintenanceDetails(int id) throws ChangeSetPersister.NotFoundException {
        Maintenance maintenanceDetails = getMaintenanceDetailsById(id);
        maintenanceRepository.delete(maintenanceDetails);
    }



//    @Override
//    public void updateMaintenancePayment(int apartmentId, Double amountPaid)

    @Override
    public void updateMaintenancePayment(int maintenanceId, Double amountPaid) {
        Maintenance maintenance = maintenanceRepository.findById(maintenanceId)
                .orElseThrow(EntityNotFoundException::new);

        // If maintenance is already marked as PAID, throw an error
        if (maintenance.getStatus() == Maintenance.Status.PAID) {
            throw new IllegalStateException("Maintenance record is already marked as PAID");
        }
        if (maintenance.getStatus() == Maintenance.Status.DEFAULTED) {
            throw new IllegalStateException("Maintenance record is already marked as DEFAULTED");
        }

        // Retrieve all invoices related to the maintenance record
//        List<Invoice> invoices = invoiceRepository.findByMaintenanceId(maintenanceId);


        // Calculate the total amount paid from the invoices
        Double totalAmountPaid = (maintenance.getAmountPaid() != null ? maintenance.getAmountPaid() : 0.0) + amountPaid;

        if (totalAmountPaid >= maintenance.getAmount()) {
            // If total amount paid is equal to or greater than maintenance amount, mark as PAID
            maintenance.setAmountPaid(maintenance.getAmount());
            maintenance.setStatus(Maintenance.Status.PAID);
        } else if (totalAmountPaid > 0) {
            // If some amount is paid but not enough to cover the full maintenance amount, mark as PARTIALLY_PAID
            maintenance.setAmountPaid(totalAmountPaid);
            maintenance.setStatus(Maintenance.Status.PARTIALLY_PAID);
        }
        createInvoice(maintenance, amountPaid);
        maintenanceRepository.save(maintenance);
//        createInvoice(maintenance, amountPaid);
    }


    @Override
    public void createInvoice(Maintenance maintenance, Double amountPaid) {
        Invoice invoice = new Invoice();
        invoice.setMaintenance(maintenance);
        invoice.setBillAmount(maintenance.getAmount());
        invoice.setAmountPaid(amountPaid);
        invoice.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        invoiceRepository.save(invoice);
    }

    @Override
    public void createNextMonthMaintenance() {
        List<Maintenance> existingMaintenances = maintenanceRepository.findAll();
        existingMaintenances.forEach(existingMaintenance -> {
            LocalDateTime nextDueDate = existingMaintenance.getDueDate().toLocalDateTime().plusMonths(1);
            double nextMonthAmount = 1000.0;
            Double amountPaid = existingMaintenance.getAmountPaid() != null ? existingMaintenance.getAmountPaid() : 0.0;

            if (existingMaintenance.getStatus() != Maintenance.Status.PAID) {
                double remainingAmount = existingMaintenance.getAmount() - amountPaid;

                if (remainingAmount > 0) {
                    // Update status to DEFAULTED if amount is not paid in full
                    existingMaintenance.setStatus(Maintenance.Status.DEFAULTED);
                    maintenanceRepository.save(existingMaintenance);
                    // Include remaining amount and penalty in the next month's maintenance amount
                    nextMonthAmount += remainingAmount + 800;
                }
            }
            Maintenance newMaintenance = new Maintenance();
            newMaintenance.setApartment(existingMaintenance.getApartment());
            newMaintenance.setAmount(nextMonthAmount);
            newMaintenance.setDueDate(Timestamp.valueOf(nextDueDate));
            newMaintenance.setStatus(Maintenance.Status.PENDING);
            maintenanceRepository.save(newMaintenance);
        });
    }
}

