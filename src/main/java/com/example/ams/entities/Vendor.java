package com.example.ams.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vendorId;
    private String name;
    private String type;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    @JsonIgnore()
    private Apartment apartment;

//    @OneToMany(mappedBy = "vendor", cascade = CascadeType.PERSIST)
//    List<JobSheet> jobSheets;

}
