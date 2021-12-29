package com.example.robwarehouse.repository;

import com.example.robwarehouse.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository <Order, Long> {

    List<Order> findAllByCustomerIdOrderByCreationDateDesc(Long customerId);
}
