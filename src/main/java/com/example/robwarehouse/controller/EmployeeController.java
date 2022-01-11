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
@RequestMapping("/employees")

public class EmployeeController {

    private final EmployeeSerivice employeeSerivice;
    private final EmployeeRepo employeeRepo;


    @GetMapping("/add")
    public String createEmployee(Model model){
        model.addAttribute("employee", new Employee());
        return "createEmployeeForm";
    }

    @PostMapping("/add")
    public String createEmployee(@Valid @ModelAttribute Employee employee){

        return employeeSerivice.createEmployee(employee);
    }

    @GetMapping("/editEmployee/{id}")
    public String editEmployees(@PathVariable Long id, Model model){
        model.addAttribute("employeeId", id);
        var employee = employeeRepo.getById(id);
        model.addAttribute("editEmployee", employee);
        return "editEmployee";
    }
    @PostMapping("/editEmployee/{id}")
    public String editEmployees(@PathVariable Long id,
                                @RequestParam(name = "employeeName") String employeeName,
                                @RequestParam(name = "employeeSurname") String employeeSurname,
                                @RequestParam(name = "employeeAddress") String employeeAddress,
                                @RequestParam(name = "employeeUsername") String employeeUsername,
                                @RequestParam(name = "employeeRole") Role employeeRole){
        employeeSerivice.editEmployee(id,employeeName,employeeSurname,employeeAddress,employeeUsername,employeeRole);

        return "redirect:/employees/employee/" + id;
    }
    @GetMapping("/allEmployees")
    public String getAll(Model model){
        Collection<Employee> employees = this.employeeSerivice.getAll();
        model.addAttribute("employees", employees);

        return "employeeList";

    }
    @GetMapping("/deleteEmployee/{id}")
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
