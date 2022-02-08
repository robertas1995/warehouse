package com.example.robwarehouse.service;


import com.example.robwarehouse.dto.StatiscicsBody;
import com.example.robwarehouse.model.Employee;
import org.springframework.stereotype.Service;

@Service
public interface StatisticService {

    double getSumFromSales();

    Long getCountByEmoloyeeId(Long id);

    StatiscicsBody getStatsBody(Employee employee, Long orderCount);

}
