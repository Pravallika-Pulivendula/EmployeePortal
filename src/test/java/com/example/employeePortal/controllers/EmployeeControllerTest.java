package com.example.employeePortal.controllers;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldFindEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setEmpId(1);
        given(employeeService.getEmployeeById(employee.getEmpId())).willReturn(employee);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/{id}", employee.getEmpId()))
                .andExpect(status().isOk());
    }
}