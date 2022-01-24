package com.example.robwarehouse.service;

import com.example.robwarehouse.model.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public interface OrderItemService {

    OrderItem save(OrderItem orderItem);

    Optional<OrderItem> getById(Long id);

    void delete(OrderItem orderItem);

    Long editItem(Long id, OrderItem orderItem);
}
