package com.example.employeePortal.entities;

import lombok.Data;

import java.time.LocalDate;


@Data
public class Employee {
    private long empId;
    private String firstName;
    private String lastName;
    private String everestEmailId;
    private String password;
    private String personalEmailId;
    private LocalDate dob;
    private LocalDate doj;
    private String designation;
    private int experienceInYears;
    private String bio;
    private EmployeeAddress presentAddress;
    private EmployeeAddress permanentAddress;
}
