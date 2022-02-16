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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "/search")
    public Page<Employee> findByName(@RequestParam("query") String searchKeyword, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return employeeService.findByName(searchKeyword, pageable);
    }
}
