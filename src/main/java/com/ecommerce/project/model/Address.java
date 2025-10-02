package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name must be at least 5 characters")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name must be at least 5 characters")
    private String buildingName;


    @NotBlank
    @Size(min = 4, message = "City name must be at least 4 characters")
    private String cityName;

    @NotBlank
    @Size(min = 4, message = "State name must be at least 4 characters")
    private String stateName;

    @NotBlank
    @Size(min = 2, message = "Country name must be at least 2 characters")
    private String country;

    @NotBlank
    @Size(min = 5, message = "Pin code must be at least 5 characters")
    private String pinCode;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;


    public Address(String street, String buildingName, String cityName, String stateName, String country, String pinCode) {
        this.street = street;
        this.buildingName = buildingName;
        this.cityName = cityName;
        this.stateName = stateName;
        this.country = country;
        this.pinCode = pinCode;
    }
}
