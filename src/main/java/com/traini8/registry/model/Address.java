package com.traini8.registry.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private String detailedAddress;
    private String city;
    private String state;
    private String pincode;

    public Address(@NotBlank(message = "Detailed address is required") String detailedAddress, @NotBlank(message = "City is required") String city, @NotBlank(message = "State is required") String state, @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid Pincode format") String pincode) {
    }
}
