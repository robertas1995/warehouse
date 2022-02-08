package com.example.robwarehouse.service;


import com.example.robwarehouse.dto.StatiscicsBody;
import com.example.robwarehouse.model.Employee;
import com.example.robwarehouse.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService{

    private final OrderSevice orderSevice;

    @Override
    public double getSumFromSales(){
        List<Order> allOrders = orderSevice.getAll().stream().toList();
        Double allPrice = allOrders.stream().map(Order::getTotalPrice).mapToDouble(Double::doubleValue).sum();
      return allPrice;
    }

    @Override
    public Long getCountByEmoloyeeId(Long id){
        return  orderSevice.getCountByEmoloyeeId(id);
    }

    @Override
    public StatiscicsBody getStatsBody(Employee employee, Long orderCount) {
        return new StatiscicsBody(employee,orderCount);
    }

}
