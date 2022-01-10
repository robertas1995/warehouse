package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Customer;
import com.example.robwarehouse.model.Order;
import com.example.robwarehouse.model.OrderItem;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public interface OrderSevice {


    Optional<Order> createNewOrder(Order createdOrder, Customer customer, List<OrderItem> orderItems);

    Optional<Order> getById(Long id);

    String addItem(Order createdOrder);
}
