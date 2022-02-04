package com.example.employeePortal.controllers;


import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.repositories.EmployeeInMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeInMemoryRepository employeeInMemoryRepository;

    @GetMapping(value = "")
    public List<Employee> getAllEmployees() {
        return employeeInMemoryRepository.getAllEmployees();
    }

    @GetMapping(value = "/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id") Long id) {
        return employeeInMemoryRepository.getEmployeeById(id);
    }

    @PostMapping("")
    public Employee addEmployee(@RequestBody Employee employee) {
       return employeeInMemoryRepository.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable(name = "id") Long id, @RequestBody Employee employee) {
        return employeeInMemoryRepository.updateEmployee(id,employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(name = "id") Long id) {
       employeeInMemoryRepository.deleteEmployee(id);
    }
}
