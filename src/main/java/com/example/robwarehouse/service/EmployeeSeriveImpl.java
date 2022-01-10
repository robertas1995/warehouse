package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Employee;
import com.example.robwarehouse.model.Role;
import com.example.robwarehouse.repository.EmployeeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeSeriveImpl implements EmployeeSerivice {

    private final EmployeeRepo employeeRepo;

    @Override
    public Optional<Employee> getById(Long id){ return employeeRepo.findById(id);}
    @Override
    public  String createEmployee(Employee createdEmployee){
        Employee employee = new Employee();
        employee.setName(createdEmployee.getName());
        employee.setSurname(createdEmployee.getSurname());
        employee.setAddress(createdEmployee.getAddress());
        employee.setUsername(createdEmployee.getUsername());
        employee.setRole(createdEmployee.getRole());
        employeeRepo.save(employee);

        return  "home";

    }

    @Override
    public  void editEmployee(Long id, String employeeAddress, String employeeName, Role employeeRole, String employeeSurname, String employeeUsername){
        var employee = employeeRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("emplyee not found"));
        employee.setAddress(employeeAddress);
        employee.setName(employeeName);
        employee.setSurname(employeeSurname);
        employee.setUsername(employeeUsername);
        employee.setRole(employeeRole);
        employeeRepo.save(employee);
    }

    @Override
    public Collection<Employee> getAll(){return employeeRepo.findAll();}

    @Override
    public void delete(Employee employee){employeeRepo.delete(employee);}


}
