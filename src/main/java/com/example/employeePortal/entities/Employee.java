package com.example.employeePortal.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(name = "everest_email_id")
    private String everestEmailId;

    @Column(name = "password")
    private String password;

    @Email
    @Column(name = "personal_email_id")
    private String personalEmailId;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "doj")
    private LocalDate doj;

    @Column(name = "designation")
    private String designation;

    @Min(value = 0, message = "Should be great than 0")
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
