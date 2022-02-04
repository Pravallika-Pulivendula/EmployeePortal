package com.example.employeePortal.entities;

import lombok.Data;

@Data
public class EmployeeAddress {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private long zipcode;
    private String country;
}
