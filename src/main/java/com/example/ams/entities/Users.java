package com.example.ams.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//user_id SERIAL [pk]
//name VARCHAR
//email VARCHAR
//apartment_id INT [ref: > apartments.apartment_id]
//role enum

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Users {
    @Id
    private Integer userId;
    private String userName;
    private String email;
    private Integer apartmentId;
    private Role role;
}


