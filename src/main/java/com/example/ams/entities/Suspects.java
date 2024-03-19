package com.example.ams.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//suspect_id SERIAL [pk]
//crime_id INT [ref: > crime_records.record_id]
//user_id INT [ref: > users.user_id]

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suspects {
    @Id
    private Integer suspectId;
    private Integer crimeId;
    private Integer userId;
}
