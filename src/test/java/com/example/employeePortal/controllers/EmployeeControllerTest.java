package com.example.employeePortal.controllers;

import com.example.employeePortal.entities.Address;
import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateNewEmployee() throws Exception {
        given(employeeService.addEmployee(any(Employee.class))).willAnswer((invocation) -> invocation.getArgument(0));
        Address presentAddress = new Address(1,"3-116","ys venkat reddy nagar","pulivendula","ap",516390,"in");
        Address permanentAddress = new Address(2,"3-116","ys venkat reddy nagar","pulivendula","ap",516390,"in");
        Employee newEmployee = new Employee(1,"prav","reddy","prav@gmail.com","pravk","pravallika@gmail.com", LocalDate.of(2000,7,7),LocalDate.of(2021,8,2),"se",1,"hello",presentAddress,permanentAddress);
        this.mockMvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}