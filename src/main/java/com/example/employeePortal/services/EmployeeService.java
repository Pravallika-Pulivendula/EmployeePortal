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
        Sort sort = Sort.by(Sort.Direction.ASC, "doj");
        return employeeRepository.findAll(sort);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployee(Long id, Employee oldEmployee) {
        Employee employee = employeeRepository.findById(oldEmployee.getEmpId()).orElse(oldEmployee);
        employee.setEmpId(id);
        employeeRepository.save(employee);
        return employee;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}

