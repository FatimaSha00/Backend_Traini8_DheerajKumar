package com.traini8.registry.dto;

import com.traini8.registry.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingCenterDTO {
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
