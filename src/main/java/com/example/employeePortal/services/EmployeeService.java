package com.example.employeePortal.services;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.exceptions.EmployeeExistsException;
import com.example.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findById(empId).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEverestEmailId(employee.getEverestEmailId());
        if (optionalEmployee.isPresent()) {
            throw new EmployeeExistsException("Employee with email " + employee.getEverestEmailId() + " already exists");
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee oldEmployee = employeeRepository.findById(id).orElseThrow();
        employee.setEmpId(oldEmployee.getEmpId());
        return employeeRepository.save(employee);
    }

    public Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    public Page<Employee> findByName(String searchName, Pageable pageable) {
        return employeeRepository.findByFirstNameContainingOrLastNameContaining(searchName, searchName, pageable);
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

}

