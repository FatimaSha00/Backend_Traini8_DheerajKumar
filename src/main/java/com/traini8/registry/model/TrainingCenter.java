package com.traini8.registry.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "training_centers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndex(def = "{'centerCode': 1}", unique = true)
public class TrainingCenter {

    @Id
    private String id;
    
    private String centerName;

    @Indexed(unique = true)
    private String centerCode;
    private Address address;
    private int studentCapacity;
    private List<String> coursesOffered;
    private Instant createdOn;
    private String contactEmail;
    private String contactPhone;

    public TrainingCenter(@NotBlank(message = "Center name is required") @Size(max = 40, message = "Center name must be less than 40 characters") String centerName, @NotBlank(message = "Center code is required") @Pattern(regexp = "^[a-zA-Z0-9]{12}$", message = "Center code must be exactly 12 alphanumeric characters") String centerCode, Address address, @Min(value = 1, message = "Student capacity must be greater than 0") int studentCapacity, List<String> coursesOffered, Instant instant, @Email(message = "Invalid email format") String contactEmail, @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number format") String contactPhone) {
    }
}
