package com.example.employeePortal.services;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.exceptions.EmployeeExistsException;
import com.example.employeePortal.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {
    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp(){
        employeeRepository = mock(EmployeeRepository.class);
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void shouldSaveEmployeeSuccessfully(){
        Employee newEmployee = new Employee();
        newEmployee.setEverestEmailId("pravallikareddy@gmail.com");
        given(employeeRepository.findByEverestEmailId(newEmployee.getEverestEmailId())).willReturn(Optional.empty());
        given(employeeRepository.save(any(Employee.class))).willReturn(newEmployee);
        final Employee savedEmployee = employeeService.addEmployee(newEmployee);
        assertThat(savedEmployee.getEmpId()).isNotNull();
        verify(employeeRepository).save(any(Employee.class));
    }

    @Test
    void shouldThrowAnExceptionWhenEmployeeWithEmailAlreadyExists(){
        Employee newEmployee = new Employee();
        newEmployee.setEverestEmailId("pravallikareddy@gmail.com");
        given(employeeRepository.findByEverestEmailId(newEmployee.getEverestEmailId())).willReturn(Optional.of(newEmployee));
        assertThrows(EmployeeExistsException.class, () -> employeeService.addEmployee(newEmployee));
        verify(employeeRepository,never()).save(any(Employee.class));
    }

}