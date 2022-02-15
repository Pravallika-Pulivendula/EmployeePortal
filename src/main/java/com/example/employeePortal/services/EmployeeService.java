package com.example.employeePortal.services;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
  
  public Employee updateEmployee(Long id, Employee oldEmployee) {
        Employee employee = employeeRepository.findById(oldEmployee.getEmpId()).orElse(oldEmployee);
        employee.setEmpId(id);
        employeeRepository.save(employee);
        return employee;
    }
  
    public List<Employee> getAllEmployees(Sort sort) {
        return employeeRepository.findAll(sort);
    }
    public Employee addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findById(empId).orElse(null);
    }

    public Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }
}

