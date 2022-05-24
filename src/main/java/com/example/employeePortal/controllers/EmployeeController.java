package com.example.employeePortal.controllers;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.models.EmployeeResponse;
import com.example.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value = "")
    public EmployeeResponse getAllEmployees(Pageable pageable) {
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
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        final Employee newEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "id") Long id, @RequestBody Employee employee) {
        Employee oldEmployee = employeeService.getEmployeeById(id);
        if (oldEmployee != null) {
            return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = "/search")
    public EmployeeResponse findByName(@RequestParam("query") String searchKeyword, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Employee> employeePage = employeeService.findByName(searchKeyword, pageable);
        return new EmployeeResponse(employeePage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(name = "id") long id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(employee);
    }
}
