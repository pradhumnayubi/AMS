package com.example.ams.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Data
@Entity
public class AmenityRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reqId;

    private String requestType;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User user;

    @OneToMany(mappedBy = "amenityRequest", cascade = CascadeType.PERSIST)
    List<JobSheet> jobSheets;
}


