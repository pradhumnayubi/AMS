package com.example.ams.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

//id SERIAL [pk]
//owner_id INT //[ref: > owner_resident.owner_id]
//apartment_id INT [ref: - apartments.apartment_id]
//amount DECIMAL
//date TIMESTAMP

public class MaintenanceDetails {
    @Id
    private Integer id;
    private Integer ownerId;
    private Integer apartmentId;
    private Double amount;
    private LocalDateTime date;
}
