package com.example.ams.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Entity
public class GateLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logId;

    private Timestamp checkInTime;

    private Timestamp checkOutTime;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    @JsonIgnoreProperties({"gateLogs","users","layout"})
    private Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"gateLogs","apartment","email"})
    private User user;
}

