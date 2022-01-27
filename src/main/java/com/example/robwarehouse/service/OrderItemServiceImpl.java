package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Order;
import com.example.robwarehouse.model.OrderItem;
import com.example.robwarehouse.model.Product;
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
    private final ProductService productService;
    private final PositionService positionService;
    private final OrderServiceImpl orderService;


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

        Product product = productService.get(orderItem.getProduct().getId());
        orderItemRepo.delete(orderItem);
        var order = orderRepo.getById(orderItem.getOrderId());
        Double allItems = orderItemRepo.findByOrderId(orderItem.getOrderId()).stream()
                .map(x -> x.getPrice())
                .collect(Collectors.summingDouble(Double::doubleValue));
        order.setTotalPrice(allItems);
        orderRepo.save(order);
        positionService.recalculatePositionsAndReturn(product.getId(), orderItem.getQuantity());
    }

    @Override
    public Long editItem(Long id, OrderItem orderItem) {

        Order order = orderRepo.getById(orderItem.getOrderId());
        Product product = productService.get(orderItem.getProduct().getId());
        OrderItem editItem = orderItemRepo.findById(id).orElseThrow();
        Integer oldQuantity = editItem.getQuantity();
        positionService.recalculatePositionsAndReturn(product.getId(), oldQuantity);
        editItem.setQuantity(orderItem.getQuantity());
        editItem.setProduct(orderItem.getProduct());
        editItem.setPrice(orderItem.getProduct().getPrice() * orderItem.getQuantity());
        orderItemRepo.save(editItem);
        orderService.updateOrderTotalPrice(orderItem.getOrderId(), order);
        positionService.recalculatePositionsForProduct(product.getId(), orderItem.getQuantity());

        return editItem.getId();
    }


}
