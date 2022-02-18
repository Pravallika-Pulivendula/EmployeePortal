package com.example.employeePortal.controllers;


import com.example.employeePortal.models.EmployeeResponse;
import com.example.employeePortal.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
