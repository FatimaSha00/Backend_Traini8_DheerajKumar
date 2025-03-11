package com.traini8.registry.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.time.Instant;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
//@Table(name = "training_centers")
public class TrainingCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String centerName;

    @Column(unique = true)
    private String centerCode;

    @Embedded
    private Address address;
    private int studentCapacity;
    private List<String> coursesOffered;
    private Instant createdOn;
    private String contactEmail;
    private String contactPhone;
}
