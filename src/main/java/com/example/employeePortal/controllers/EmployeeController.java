package com.example.employeePortal.controllers;


import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "")
    public List<Employee> getAllEmployees(@RequestParam(value = "sort") String[] sortBy) {
        List<Order> orders = Arrays.stream(sortBy)
                .map(sortParams -> sortParams.split(","))
                .map(sorts -> new Order(employeeService.getSortDirection(sorts[1]), sorts[0]))
                .collect(Collectors.toList());
        return employeeService.getAllEmployees(Sort.by(orders));
    }

}
