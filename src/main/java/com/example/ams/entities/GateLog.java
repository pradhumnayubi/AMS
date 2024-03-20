package com.example.ams.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

//log_id SERIAL [pk]
//user_id int [ref: > users.user_id]
//check_in_time timestamp
//check_out_time timestamp
//apartment_id INT [ref: > apartments.apartment_id]

@Data
@Entity
public class GateLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private Timestamp checkInTime;

    private Timestamp checkOutTime;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}

