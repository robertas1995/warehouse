package com.example.robwarehouse.controller;

import com.example.robwarehouse.dto.StatiscicsBody;
import com.example.robwarehouse.model.Employee;
import com.example.robwarehouse.repository.EmployeeRepo;
import com.example.robwarehouse.repository.OrderRepo;
import com.example.robwarehouse.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/statistics")
public class StatisticController {

    private final StatisticService statisticService;
    private final OrderRepo orderRepo;
    private final EmployeeRepo employeeRepo;

    public StatisticController(StatisticService statisticService, OrderRepo orderRepo, EmployeeRepo employeeRepo) {
        this.statisticService = statisticService;
        this.orderRepo = orderRepo;
        this.employeeRepo = employeeRepo;
    }


    @GetMapping("/")
    public String salesStatistic(Model model) {
        List<Employee> employeeList = employeeRepo.findAll();
        List<StatiscicsBody> statBodyList = new ArrayList<>();
        employeeList.forEach(employee -> {
            statBodyList.add(statisticService.getStatsBody(employee,statisticService.getCountByEmoloyeeId(employee.getId())));
        });
        model.addAttribute("statsBody", statBodyList);

        return "statistics";
    }


}
