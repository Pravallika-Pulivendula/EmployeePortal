package com.example.employeePortal.controllers;


import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "/search")
    public Map<String, Object> findByName(@RequestParam("query") String searchKeyword, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Employee> employeePage = employeeService.findByName(searchKeyword, pageable);
        Map<String, Object> employeeResponse = new HashMap<>();
        employeeResponse.put("data",employeePage.getContent());
        employeeResponse.put("totalElements",employeePage.getTotalElements());
        employeeResponse.put("totalPages",employeePage.getTotalPages());
        employeeResponse.put("pageSize",employeePage.getSize());
        employeeResponse.put("currentPage",employeePage.getNumber());
        employeeResponse.put("hasNext",employeePage.hasNext());
        employeeResponse.put("hasPrevious",employeePage.hasPrevious());
        return employeeResponse;
    }
}
