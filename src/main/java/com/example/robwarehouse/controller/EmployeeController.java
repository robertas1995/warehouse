package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.Employee;
import com.example.robwarehouse.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/employees")
public class EmployeeController extends AbstractCrudController<Employee>{

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService){
        super("employees", employeeService);
        this.employeeService = employeeService;

    }


}
