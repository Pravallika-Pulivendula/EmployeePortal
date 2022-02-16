package com.example.employeePortal.services;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Page<Employee> findByName(String searchName, Pageable pageable) {
        return employeeRepository.findByFirstNameContainingOrLastNameContaining(searchName, searchName,pageable);
    }
}

