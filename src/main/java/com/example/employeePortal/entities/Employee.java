package com.example.employeePortal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "present_address_id")
    private Address presentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permanent_address_id")
    private Address permanentAddress;

}
