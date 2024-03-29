package com.example.ams.entities;

import javax.persistence.*;

import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenance;

    private Double billAmount;

    private Double amountPaid;

    private Timestamp createdAt;

    // Constructors, getters, and setters
}
