package com.example.employeePortal.services;

import com.example.employeePortal.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class EmployeeServiceTest {
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void shouldReturnDescDirection() {
        assertTrue(employeeService.getSortDirection("desc").isDescending());
    }

    @Test
    void shouldNotReturnDescDirection() {
        assertFalse(employeeService.getSortDirection("desc").isAscending());
    }
}