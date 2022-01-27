package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Employee;
import com.example.robwarehouse.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@AllArgsConstructor
public class EmployeeServeImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Override
    public Employee get(Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    @Override
    public Long create(Employee createdEmployee) {
        Employee employee = new Employee();
        employee.setName(createdEmployee.getName());
        employee.setSurname(createdEmployee.getSurname());
        employee.setAddress(createdEmployee.getAddress());
        employee.setUsername(createdEmployee.getUsername());
        employee.setRole(createdEmployee.getRole());
        employeeRepo.save(employee);

        return employee.getId();

    }

    @Override
    public void update(Employee update) {
        var editedEmployee = employeeRepo.findById(update.getId()).orElseThrow(() -> new EntityNotFoundException("emplyee not found"));
        editedEmployee.setName(editedEmployee.getName());
        editedEmployee.setSurname(editedEmployee.getSurname());
        editedEmployee.setAddress(editedEmployee.getAddress());
        editedEmployee.setUsername(editedEmployee.getUsername());
        editedEmployee.setRole(editedEmployee.getRole());
        employeeRepo.save(update);
    }

    @Override
    public Collection<Employee> getAll() {
        return employeeRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        employeeRepo.deleteById(id);
    }


}
