package com.example.robwarehouse.service;

import com.example.robwarehouse.model.OrderItem;
import com.example.robwarehouse.repository.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderItemService {

    @Autowired
    private OrderItemRepo orderItemRepo;

    public void addOrderProducts(OrderItem orderItem){
        orderItemRepo.save(orderItem);
    }
}
