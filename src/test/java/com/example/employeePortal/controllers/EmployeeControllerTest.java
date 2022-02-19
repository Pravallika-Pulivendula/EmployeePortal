package com.example.employeePortal.controllers;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldFetchEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setEmpId(1);
        employee.setFirstName("pravallika");
        when(employeeService.getEmployeeById(employee.getEmpId())).thenReturn(employee);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employee.getEmpId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo(employee.getFirstName())));
    }

    @Test
    void shouldReturn404ErrorWhenNoEmployeeWithGivenIdIsFound() throws Exception {
        long empId = 2;
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", empId))
                .andExpect(status().isNotFound());
    }
}