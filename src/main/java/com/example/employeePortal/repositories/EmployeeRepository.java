package com.example.employeePortal.repositories;

import com.example.employeePortal.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}