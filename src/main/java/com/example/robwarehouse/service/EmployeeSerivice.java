package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Employee;
import com.example.robwarehouse.model.Role;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface EmployeeSerivice {


    Optional<Employee> getById(Long id);

    String createEmployee(Employee createdEmployee);


    void editEmployee(Long employeeId, String employeeName, String employeeSurname, String employeeAddress, String employeeUsername, Role employeeRole);

    Collection<Employee> getAll();

    void delete(Employee employee);
}
