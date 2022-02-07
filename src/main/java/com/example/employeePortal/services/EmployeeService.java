package com.example.employeePortal.services;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    public EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        Sort sort = Sort.by(Sort.Direction.ASC, "doj", "firstName");
        return employeeRepository.findAll(sort);
    }
}

