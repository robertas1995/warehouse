package com.example.robwarehouse.dto;

import com.example.robwarehouse.model.Employee;
import lombok.Data;

@Data
public class StatiscicsBody {
    private Employee employee;
    private Long orderCount;

    public StatiscicsBody(Employee employee, Long orderCount) {
        this.employee = employee;
        this.orderCount = orderCount;
    }
}
