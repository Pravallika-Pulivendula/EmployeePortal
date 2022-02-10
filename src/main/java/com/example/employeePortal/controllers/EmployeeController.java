package com.example.employeePortal.controllers;


import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.exceptions.EmployeeNotFoundException;
import com.example.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "/by-name")
    public List<Employee> findByName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        List<Employee> employeesList = employeeService.findByName(firstName, lastName);
        if(employeesList.isEmpty()){
            throw new EmployeeNotFoundException("No employee found with the name: "+firstName+lastName);
        }
        return employeesList;
    }
}
