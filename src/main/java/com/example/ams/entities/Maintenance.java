package com.example.ams.entities;

import javax.persistence.*;
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
