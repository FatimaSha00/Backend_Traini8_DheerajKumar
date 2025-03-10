package com.traini8.registry.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingCenterRequestDTO {

    @NotBlank(message = "Center name is required")
    @Size(max = 40, message = "Center name must be less than 40 characters")
    private String centerName;

    @NotBlank(message = "Center code is required")
    @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "Center code must be exactly 12 alphanumeric characters")
    private String centerCode;

    @NotNull(message = "Address is required")
    private AddressDTO address;

    @Min(value = 1, message = "Student capacity must be greater than 0")
    private int studentCapacity;

    private List<String> coursesOffered;

    @Email(message = "Invalid email format")
    private String contactEmail;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number format")
    private String contactPhone;
}
