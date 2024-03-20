package com.example.ams.entities;


import jakarta.persistence.*;
import lombok.Data;


//user_id SERIAL [pk]
//name VARCHAR
//email VARCHAR
//apartment_id INT [ref: > apartments.apartment_id]
//role enum

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Enumerated(EnumType.STRING)
    private Role role;
}

enum Role {

    OWNER,
    RESIDENT,
    GUEST
}



