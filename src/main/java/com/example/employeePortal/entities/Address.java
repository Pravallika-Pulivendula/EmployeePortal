package com.example.employeePortal.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private long zipcode;
    private String country;
}
