package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.Employee;
import com.example.robwarehouse.model.Role;
import com.example.robwarehouse.repository.EmployeeRepo;
import com.example.robwarehouse.service.EmployeeSerivice;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Data
@Controller

public class EmployeeController {

    private final EmployeeSerivice employeeSerivice;
    private final EmployeeRepo employeeRepo;


    @GetMapping("employee/add")
    public String createEmployee(Model model){
        model.addAttribute("employee", new Employee());
        log.error("can't create tamplate");
        return "createEmployeeForm";
    }

    @PostMapping("employee/add")
    public String createEmployee(@Valid @ModelAttribute Employee employee){

        return employeeSerivice.createEmployee(employee);
    }

    @GetMapping("employee/edit/{id}")
    public String editEmployees(@PathVariable Long id, Model model){
        model.addAttribute("employeId", id);
        var employee = employeeRepo.getById(id);
        model.addAttribute("editEmployee", employee);
        return "/employee";
    }
    @PostMapping("employee/edit/{id}")
    public String editEmployees(@PathVariable Long id,
                                @RequestParam(name = "employeeAddress") String employeeAddress,
                                @RequestParam(name = "employeeName") String employeeName,
                                @RequestParam(name = "employeeRole") Role employeeRole,
                                @RequestParam(name = "employeeSurname") String employeeSurname,
                                @RequestParam(name = "employeeUsername") String employeeUsername){
        employeeSerivice.editEmployee(id, employeeAddress,employeeSurname,employeeRole,employeeUsername,employeeAddress);
        return "redirect:/employee/" + id;

    }
    @GetMapping("allEmployees")
    public String getAll(Model model){
        Collection<Employee> employeeCollection = this.employeeSerivice.getAll();
        model.addAttribute("employee", employeeCollection);

        return "allEmployees";

    }
    @GetMapping("/deleteEmployee{id}")
    public String deleteEmployee(@PathVariable Long id){

        Optional<Employee> optionalEmployee = this.employeeSerivice.getById(id);
        Employee employee = optionalEmployee.get();
        this.employeeSerivice.delete(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/{id}")
    public String getEmployee(@PathVariable Long id, Model model){
        Optional<Employee> optionalEmployee = this.employeeSerivice.getById(id);
        Employee employee = optionalEmployee.get();
        model.addAttribute("employee", employee);
        return "employee";
    }



}
