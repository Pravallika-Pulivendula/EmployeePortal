package com.example.employeePortal.controllers;

import com.example.employeePortal.entities.Address;
import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {
    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    private List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        this.employeeList = new ArrayList<>();
        Address presentAddress = new Address(1, "3-116", "ys venkat reddy nagar", "pulivendula", "ap", 516390, "in");
        Address permanentAddress = new Address(2, "3-116", "ys venkat reddy nagar", "pulivendula", "ap", 516390, "in");
        this.employeeList.add(new Employee(1, "prav", "reddy", "prav@gmail.com", "pravk", "pravallika@gmail.com", LocalDate.of(2000, 7, 7), LocalDate.of(2021, 8, 2), "se", 1, "hello", presentAddress, permanentAddress));
        this.employeeList.add(new Employee(2, "pravallika", "pulivendula", "pravallika@gmail.com", "pravak", "pravallikap@gmail.com", LocalDate.of(2000, 6, 6), LocalDate.of(2021, 1, 3), "sed", 1, "hi", presentAddress, permanentAddress));
    }

    @Test
    void shouldFetchAllEmployees() throws Exception {
        Page<Employee> employeePage = new PageImpl<>(this.employeeList);
        when(employeeService.getAllEmployees(any(Pageable.class))).thenReturn(employeePage);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()", is(this.employeeList.size())))
                .andExpect(jsonPath("$.currentPage", equalTo(0)))
                .andExpect(jsonPath("$.hasNext", equalTo(false)))
                .andExpect(jsonPath("$.totalPages", equalTo(1)));

        verify(employeeService).getAllEmployees(any(Pageable.class));
    }

}