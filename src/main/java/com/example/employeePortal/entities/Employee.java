package com.example.employeePortal.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long empId;

    @NotBlank(message = "Firstname should not be blank")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Lastname should not be blank")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email should not be blank")
    @Email
    @Column(name = "everest_email_id", nullable = false)
    private String everestEmailId;

    @Column(name = "password", nullable = false)
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
