package com.example.ams.entities;


import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
public class JobSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serviceId;

    @Enumerated(EnumType.STRING)
    private Status status;
    private Timestamp ETA;

    @ManyToOne
    @JoinColumn(name = "amenity_request_id")
    private AmenityRequest amenityRequest;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
    enum Status{
        WAITING,
        IN_PROGRESS,
        COMPLETED
    }
}




