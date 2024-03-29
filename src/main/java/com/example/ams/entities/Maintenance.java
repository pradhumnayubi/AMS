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

    private Timestamp dueDate;

    private Double amountPaid;

    @OneToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status{
        PAID,
        PENDING,
        PARTIALLY_PAID,

        DEFAULTED
    }

}
