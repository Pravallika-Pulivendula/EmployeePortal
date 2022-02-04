package com.example.employeePortal.repositories;

import com.example.employeePortal.entities.Employee;
import com.example.employeePortal.exceptions.EmployeeNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmployeeInMemoryRepository {
    private AtomicLong NEXT_ID = new AtomicLong(0);
    private Map<Long, Employee> EMPLOYEES = new HashMap<>();

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(EMPLOYEES.values());
    }

    public Employee getEmployeeById(Long id) {
        if (!EMPLOYEES.containsKey(id)) throw new EmployeeNotFoundException();
        return EMPLOYEES.get(id);
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmpId(NEXT_ID.incrementAndGet());
        EMPLOYEES.put(employee.getEmpId(), employee);
        return employee;
    }

    public Employee updateEmployee(Long id, Employee employee) {
        if (!EMPLOYEES.containsKey(id)) throw new EmployeeNotFoundException();
        employee.setEmpId(id);
        EMPLOYEES.put(employee.getEmpId(), employee);
        return employee;
    }

    public void deleteEmployee(Long id) {
        if (!EMPLOYEES.containsKey(id)) throw new EmployeeNotFoundException();
        EMPLOYEES.remove(id);
    }
}
