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
        employeeRepo.save(createdEmployee);

        return  "redirect:/employee";

    }

    @Override
    public  void editEmployee(Long employeeId, String employeeName,String employeeSurname,String employeeAddress,String employeeUsername, Role employeeRole){
        var employee = employeeRepo.findById(employeeId).orElseThrow(()-> new EntityNotFoundException("emplyee not found"));
       employee.setSurname(employeeName);
       employee.setSurname(employeeSurname);
       employee.setAddress(employeeAddress);
       employee.setUsername(employeeUsername);
       employee.setRole(employeeRole);
        employeeRepo.save(employee);
    }

    @Override
    public Collection<Employee> getAll(){return employeeRepo.findAll();}

    @Override
    public void delete(Employee employee){employeeRepo.delete(employee);}


}
