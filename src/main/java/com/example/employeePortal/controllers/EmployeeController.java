package com.example.employeePortal.controllers;


import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "")
    public Slice<Employee> getAllEmployees(@RequestParam(value = "sort") String[] sortBy, @RequestParam(value = "page") int page, @RequestParam(value = "size") int pageSize) {
        List<Order> orders = Arrays.stream(sortBy)
                .map(sortParams -> sortParams.split(","))
                .map(sorts -> new Order(employeeService.getSortDirection(sorts[1]), sorts[0]))
                .collect(Collectors.toList());
        Pageable pageable = PageRequest.of(page,pageSize,Sort.by(orders));
        return employeeService.getAllEmployees(pageable);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        final Employee newEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "id") Long id, @RequestBody Employee employee) {
        Employee newEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(newEmployee);
    }

}
