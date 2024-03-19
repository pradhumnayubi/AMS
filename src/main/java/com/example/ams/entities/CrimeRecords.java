package com.example.ams.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//record_id SERIAL [pk]
//date TIMESTAMP
//apartment_id INT [ref: > apartments.apartment_id]
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrimeRecords {
    @Id
    private Integer recordId;
    private LocalDateTime date;
    private Integer apartmentId;

}
