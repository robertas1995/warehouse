package com.example.robwarehouse.repository;

import com.example.robwarehouse.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {


    List<OrderItem> findByOrderId(Long orderId);
}
