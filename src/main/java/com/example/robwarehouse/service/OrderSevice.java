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

    Long createNewOrder(Order newOrder);

    Optional<Order> getById(Long id);

    OrderItem save(OrderItem orderItem);

    Long addItem(OrderItem orderItem, Long id, Order order);

//    Long addItem(OrderItem createdOrderItem, Long orderId);

}
