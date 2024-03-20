package com.example.ams.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//vendor_id SERIAL [pk]
//name VARCHAR
//type VARCHAR
//apartment_id INT [ref: > apartments.apartment_id]

@Data
@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vendorId;
    private String name;
    private String type;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

}
