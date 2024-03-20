package com.example.ams.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.VersionedDataInputStream;

import java.sql.Timestamp;

//service_id SERIAL [pk]
//request_id INT [ref: > amenity_requests.req_id]
//vendor_id INT [ref: > vendors.vendor_id]
//status enum
//ETA TIMESTAMP
@Data
@Entity
public class JobSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceId;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private AmenityRequest amenityRequest;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Enumerated(EnumType.STRING)
    private Status status;
    private Timestamp ETA;

}

enum Status{
    WAITING,
    IN_PROGRESS,
    COMPLETED
}


