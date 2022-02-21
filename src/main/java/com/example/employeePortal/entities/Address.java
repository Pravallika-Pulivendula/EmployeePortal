package com.example.employeePortal.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;

    @NotBlank(message = "Address should not be blank")
    @Column(nullable = false)
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "City should not be blank")
    @Column(nullable = false)
    private String city;

    @NotBlank(message = "State should not be blank")
    @Column(nullable = false)
    private String state;

    @Column(nullable = false, length = 20)
    private long zipcode;

    @NotBlank(message = "Country should not be blank")
    @Column(nullable = false)
    private String country;
}
