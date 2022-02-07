package com.example.employeePortal.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "empid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "everestemailid")
    private String everestEmailId;

    @Column(name = "password")
    private String password;

    @Column(name = "personalemailid")
    private String personalEmailId;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "doj")
    private LocalDate doj;

    @Column(name = "designation")
    private String designation;

    @Column(name = "experienceinyears")
    private int experienceInYears;

    @Column(name = "bio")
    private String bio;

}
