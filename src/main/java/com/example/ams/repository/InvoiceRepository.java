package com.example.ams.repository;

import com.example.ams.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByMaintenanceId(int maintenanceId);
}
