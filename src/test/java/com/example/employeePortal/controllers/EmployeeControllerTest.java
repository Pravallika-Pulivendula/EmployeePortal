package com.example.employeePortal.controllers;

import com.example.employeePortal.entities.Address;
import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee newEmployee;
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
    void shouldCreateNewEmployee() throws Exception {
        newEmployee = this.employeeList.get(0);
        given(employeeService.addEmployee(any(Employee.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(newEmployee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(newEmployee.getLastName())));

        verify(employeeService).addEmployee(any(Employee.class));
    }

    @Test
    void shouldFetchAllEmployees() throws Exception {
        Page<Employee> employeePage = new PageImpl<>(this.employeeList);
        when(employeeService.getAllEmployees(any(Pageable.class))).thenReturn(employeePage);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.size()", is(this.employeeList.size())))
                .andExpect(jsonPath("$.currentPage", equalTo(1)))
                .andExpect(jsonPath("$.hasNext", equalTo(false)))
                .andExpect(jsonPath("$.totalPages", equalTo(1)));

        verify(employeeService).getAllEmployees(any(Pageable.class));
    }

    @Test
    void shouldFetchEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setEmpId(1);
        employee.setFirstName("pravallika");
        when(employeeService.getEmployeeById(employee.getEmpId())).thenReturn(employee);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employee.getEmpId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(employee.getFirstName())));

        verify(employeeService).getEmployeeById(anyLong());
    }

    @Test
    void shouldReturn404ErrorWhenNoEmployeeWithGivenIdIsFound() throws Exception {
        long empId = 2;
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", empId))
                .andExpect(status().isNotFound());

        verify(employeeService).getEmployeeById(anyLong());
    }
}