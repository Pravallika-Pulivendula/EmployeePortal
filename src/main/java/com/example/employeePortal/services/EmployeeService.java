package com.example.employeePortal.services;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee updateEmployee(Long id, Employee oldEmployee) {
        Employee employee = employeeRepository.findById(oldEmployee.getEmpId()).orElse(oldEmployee);
        employee.setEmpId(id);
        employeeRepository.save(employee);
        return employee;
    }
}

