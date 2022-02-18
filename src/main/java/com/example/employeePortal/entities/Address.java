package com.example.employeePortal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
