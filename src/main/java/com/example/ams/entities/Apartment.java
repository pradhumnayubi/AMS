package com.example.ams.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.*;

import java.util.List;


@Data
@Entity
public class Apartment {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer apartmentId;
    private String address;

    @Enumerated(EnumType.STRING)
    private Layout layout;

    @OneToOne(mappedBy = "apartment", cascade = CascadeType.PERSIST)
    private Maintenance maintenance;

//    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnoreProperties({"apartment","gateLogs"})
//    private List<User> users;
//
//    @OneToMany(mappedBy = "apartment", cascade = CascadeType.REMOVE)
//    @JsonIgnoreProperties({"apartment","user"})
//    private List<GateLog> gateLogs;
//
//    @OneToMany(mappedBy = "apartment", cascade = CascadeType.PERSIST)
//    private List<Vendor> vendors;

//    @OneToOne(mappedBy = "apartment", cascade = CascadeType.ALL)
//    private Maintenance maintenance;
    private enum Layout {
        SINGLE_ROOM,
        ONE_BHK,
        TWO_BHK,
        THREE_BHK,
        DUPLEX,
        PENTHOUSE
    }
}




