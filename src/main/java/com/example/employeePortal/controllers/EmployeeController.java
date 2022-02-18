package com.example.employeePortal.controllers;


import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.models.EmployeeResponse;
import com.example.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "")
    public EmployeeResponse getAllEmployees(@RequestParam(value = "sort", defaultValue = "empId,asc") String[] sortBy, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        List<Order> orders = new ArrayList<>();
        if (sortBy[0].contains(",")) {
            orders = Arrays.stream(sortBy)
                    .map(sortParams -> sortParams.split(","))
                    .map(sorts -> new Order(employeeService.getSortDirection(sorts[1]), sorts[0]))
                    .collect(Collectors.toList());
        } else {
            orders.add(new Order(employeeService.getSortDirection(sortBy[1]), sortBy[0]));
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(orders));
        return new EmployeeResponse(employeeService.getAllEmployees(pageable));
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
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        final Employee newEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "id") Long id, @RequestBody Employee employee) {
        Employee newEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(newEmployee);
    }

    @GetMapping(value = "/search")
    public EmployeeResponse findByName(@RequestParam("query") String searchKeyword, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Employee> employeePage = employeeService.findByName(searchKeyword, pageable);
        return new EmployeeResponse(employeePage);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(name = "id") long id) {
        employeeService.deleteEmployee(id);
    }
}
