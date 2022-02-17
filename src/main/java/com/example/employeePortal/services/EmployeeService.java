package com.example.employeePortal.services;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

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
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployee(Long id, Employee oldEmployee) {
        Employee employee = employeeRepository.findById(oldEmployee.getEmpId()).orElse(oldEmployee);
        employee.setEmpId(id);
        employeeRepository.save(employee);
        return employee;
    }

    public Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    public Map<String, Object> getEmployeeResponse(Page<Employee> employeePage) {
        Map<String, Object> employeeResponse = new HashMap<>();
        employeeResponse.put("data", employeePage.getContent());
        employeeResponse.put("totalElements", employeePage.getTotalElements());
        employeeResponse.put("totalPages", employeePage.getTotalPages());
        employeeResponse.put("pageSize", employeePage.getSize());
        employeeResponse.put("currentPage", employeePage.getNumber());
        employeeResponse.put("hasNext", employeePage.hasNext());
        employeeResponse.put("hasPrevious", employeePage.hasPrevious());
        return employeeResponse;
    }
}

