package com.example.ams.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.hibernate.engine.internal.Cascade;

import java.util.List;


@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;


    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
    enum Role {
        OWNER,
        RESIDENT,
        GUEST
    }

//    @Getter(AccessLevel.NONE)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<GateLog> gateLogs;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<AmenityRequest> amenityRequests;
}





