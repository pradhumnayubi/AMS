package com.example.ams.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//apartment_id SERIAL [pk]
//name VARCHAR
//address VARCHAR
//amenities array
//layout enum
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apartments {

    @Id
    private Integer apartmentId;
    private String address;
    private static List<String> amenities;
    private Layout layout;
}



