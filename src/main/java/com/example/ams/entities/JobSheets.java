package com.example.ams.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//service_id SERIAL [pk]
//request_id INT [ref: > amenity_requests.req_id]
//vendor_id INT [ref: > vendors.vendor_id]
//status enum
//ETA TIMESTAMP

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class JobSheets {
    @Id
    private Integer serviceId;
    private Integer requestId;
    private Integer vendorId;
    private Status status;

}


