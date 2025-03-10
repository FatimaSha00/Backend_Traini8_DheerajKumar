package com.traini8.registry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCenterResponseDTO {

    private String centerName;
    private String centerCode;
    private AddressDTO address;
    private int studentCapacity;
    private List<String> coursesOffered;
    private long createdOn;
    private String contactEmail;
    private String contactPhone;
}
