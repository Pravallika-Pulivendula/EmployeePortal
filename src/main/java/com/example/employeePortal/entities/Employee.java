package com.example.employeePortal.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "everest_email_id")
    private String everestEmailId;

    @Column(name = "password")
    private String password;

    @Column(name = "personal_email_id")
    private String personalEmailId;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "doj")
    private LocalDate doj;

    @Column(name = "designation")
    private String designation;

    @Column(name = "experience_in_years")
    private int experienceInYears;

    @Column(name = "bio")
    private String bio;

}
