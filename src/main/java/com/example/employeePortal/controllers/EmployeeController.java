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

    @GetMapping(value = "/search")
    public List<Employee> findByName(@RequestParam("query") String searchKeyword) {
        List<Employee> employeesList = employeeService.findByName(searchKeyword);
        if(employeesList.isEmpty()){
            throw new EmployeeNotFoundException("No employee found with the name like: "+searchKeyword);
        }
        return employeesList;
    }
}
