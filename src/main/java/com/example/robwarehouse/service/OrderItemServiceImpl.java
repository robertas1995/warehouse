package com.example.robwarehouse.service;

import com.example.robwarehouse.model.OrderItem;
import com.example.robwarehouse.repository.OrderItemRepo;
import com.example.robwarehouse.repository.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepo orderItemRepo;
    private final OrderRepo orderRepo;

    @Override
    public OrderItem save(OrderItem orderItem) {
        return null;
    }

    @Override
    public Optional<OrderItem> getById(Long id) {
        return orderItemRepo.findById(id);
    }

    @Override
    public void delete(OrderItem orderItem) {
        orderItemRepo.delete(orderItem);
        var order = orderRepo.getById(orderItem.getOrderId());
        Double allItems = orderItemRepo.findByOrderId(orderItem.getOrderId()).stream()
                .map(x -> x.getPrice())
                .collect(Collectors.summingDouble(Double::doubleValue));
        order.setTotalPrice(allItems);
        orderRepo.save(order);
    }

    @Override
    public Long editItem(Long id, OrderItem orderItem) {
        OrderItem editItem = orderItemRepo.findById(id).orElseThrow();
        editItem.setQuantity(orderItem.getQuantity());
        editItem.setProduct(orderItem.getProduct());
        editItem.setPrice(orderItem.getProduct().getPrice()*orderItem.getQuantity());
        orderItemRepo.save(editItem);

        var order = orderRepo.getById(editItem.getOrderId());
        Double allItems = orderItemRepo.findByOrderId(editItem.getOrderId()).stream()
                .map(x -> x.getPrice())
                .collect(Collectors.summingDouble(Double::doubleValue));
        order.setTotalPrice(allItems);
        orderRepo.save(order);
        return editItem.getId();
    }
}
