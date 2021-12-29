package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Customer;
import com.example.robwarehouse.model.Employee;
import com.example.robwarehouse.model.Order;
import com.example.robwarehouse.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderSevice {

    String createNewOrder(Order createdOrder);

    String createNewOrder(Order createdOrder, Customer customer, List<OrderItem> orderItems);
}
