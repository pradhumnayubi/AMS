package com.example.ams.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;



//id SERIAL [pk]
//owner_id INT //[ref: > owner_resident.owner_id]
//apartment_id INT [ref: - apartments.apartment_id]
//amount DECIMAL
//date TIMESTAMP
@Data
@Entity
public class MaintenanceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    private Double amount;

    private Timestamp date;
}
