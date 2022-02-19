package com.example.employeePortal.services;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.exceptions.EmployeeExistsException;
import com.example.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEverestEmailId(employee.getEverestEmailId());
        if (optionalEmployee.isPresent()) {
            throw new EmployeeExistsException("Employee with email " + employee.getEverestEmailId() + " already exists");
        }
        return employeeRepository.save(employee);
    }
}

