package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Employee;

import java.util.Collection;


public interface EmployeeService extends CrudService<Employee> {

    default Employee newObject() {
        return new Employee();
    }


    Long create(Employee employee);

    Employee get(Long id);

    void update(Employee employee);

    Collection<Employee> getAll();

    void delete(Long id);
}
