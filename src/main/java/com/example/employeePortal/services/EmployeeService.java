package com.example.employeePortal.services;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Page<Employee> findByName(String searchName, Pageable pageable) {
        return employeeRepository.findByFirstNameContainingOrLastNameContaining(searchName, searchName, pageable);
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

