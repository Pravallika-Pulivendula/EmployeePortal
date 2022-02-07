package com.example.employeePortal.services;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    public EmployeeRepository employeeRepository;

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}

