package com.example.ams.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// req_id SERIAL [pk]
//    requester_id INT [ref: > users.user_id]
//    request_type VARCHAR
@Data
@Entity
public class AmenityRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reqId;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester;
    private String requestType;
}


