package com.traini8.registry.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String detailedAddress;
    private String city;
    private String state;
    private String pincode;
}
