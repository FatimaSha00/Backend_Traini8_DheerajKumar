package com.traini8.registry.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "training_centers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCenter {

    @Id
    private String id;
    private String centerName;
    private String centerCode;
    private Address address;
    private int studentCapacity;
    private List<String> coursesOffered;
    private Instant createdOn;
    private String contactEmail;
    private String contactPhone;
}
