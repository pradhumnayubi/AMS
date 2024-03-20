package com.example.ams.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//apartment_id SERIAL [pk]
//name VARCHAR
//address VARCHAR
//amenities array
//layout enum
@Data
@Entity
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer apartmentId;
    private String address;

    @ElementCollection
    private List<String> amenities;

    @Enumerated(EnumType.STRING)
    private Layout layout;
}

enum Layout {
    SINGLE_ROOM,
    ONE_BHK,
    TWO_BHK,
    THREE_BHK,
    DUPLEX,
    PENTHOUSE
}




