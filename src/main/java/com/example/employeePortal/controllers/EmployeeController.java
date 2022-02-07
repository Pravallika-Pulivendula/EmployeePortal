package com.example.employeePortal.controllers;


import com.example.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(name = "id") Long id) {
        employeeService.deleteEmployee(id);
    }
}
