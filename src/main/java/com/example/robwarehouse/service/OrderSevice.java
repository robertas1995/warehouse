package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Order;
import com.example.robwarehouse.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface OrderSevice {

    Long createNewOrder(Order newOrder);

    Optional<Order> getById(Long id);

    OrderItem save(OrderItem orderItem);

    void addItem(OrderItem orderItem, Long orderId);

    Collection<Order> getAll();

    Long editOrder(Long id, Order editOrder);

    Page<Order> getAllOrdersPageable(int pageNumber, int pageSize);

    Long getCountByEmoloyeeId(Long id);
}
