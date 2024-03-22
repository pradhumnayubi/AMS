package com.example.ams.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Double amount;

    private Timestamp date;

    @OneToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
