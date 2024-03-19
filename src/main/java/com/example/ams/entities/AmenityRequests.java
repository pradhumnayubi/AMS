package com.example.ams.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

// req_id SERIAL [pk]
//    requester_id INT [ref: > users.user_id]
//    request_type VARCHAR
public class AmenityRequests {

    @Id
    private Integer reqId;
    private Integer requestedId;
    private String requestType;
}


