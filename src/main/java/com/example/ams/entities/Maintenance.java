package com.example.ams.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


//id SERIAL [pk]
//owner_id INT //[ref: > owner_resident.owner_id]
//apartment_id INT [ref: - apartments.apartment_id]
//amount DECIMAL
//date TIMESTAMP
@Data
@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    private Double amount;

    private Timestamp date;
}
