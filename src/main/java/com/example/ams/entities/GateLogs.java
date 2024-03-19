package com.example.ams.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//log_id SERIAL [pk]
//user_id int [ref: > users.user_id]
//check_in_time timestamp
//check_out_time timestamp
//apartment_id INT [ref: > apartments.apartment_id]

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class GateLogs {
    @Id
    private Integer logId;
    private Integer userId;
    private Integer apartmentId;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
}
