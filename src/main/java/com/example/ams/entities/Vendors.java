package com.example.ams.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//vendor_id SERIAL [pk]
//name VARCHAR
//type VARCHAR
//apartment_id INT [ref: > apartments.apartment_id]

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendors {
    @Id
    private Integer vendorId;
    private String type;
    private Integer apartmentId;

}
