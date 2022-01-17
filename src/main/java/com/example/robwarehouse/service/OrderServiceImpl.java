package com.example.robwarehouse.service;

import com.example.robwarehouse.model.*;
import com.example.robwarehouse.repository.OrderItemRepo;
import com.example.robwarehouse.repository.OrderRepo;
import com.example.robwarehouse.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderSevice {

    private final OrderRepo orderRepo;
    private final OrderItemService orderItemService;
    private final OrderItemRepo orderItemRepo;
    private final ProductService productService;



    @Override
    public Long createNewOrder(Order newOrder) {
        Order order = new Order();
        order.setCustomer(newOrder.getCustomer());
        order.setStatus(Status.Checking);
        order.getCreationDate();
        order.setEmployee(newOrder.getEmployee());
        ArrayList<OrderItem> orderItems = new ArrayList();
        order.setOrderItems(newOrder.getOrderItems());
        order.setTotalPrice(newOrder.getTotalPrice());
//        for(OrderItem item: orderItems) {
//            OrderItem orderItem = new OrderItem();
//            orderItem.setId(item.getOrderId());
//            orderItem.setProduct(item.getProduct());
//            orderItem.setQuantity(item.getQuantity());
//            orderItem.setPrice(item.getPrice());
//            orderItemRepo.save(item);
//        }
        orderRepo.save(order);

        return order.getId();
    }

    @Override
    public Optional<Order> getById(Long id) {
        return orderRepo.findById(id);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepo.saveAndFlush(orderItem);
    }


    @Override
    public Long addItem(OrderItem createdOrderItem, Long orderId, Order order) {


        OrderItem orderItem = new OrderItem();
       orderItem.setOrder(orderRepo.getById(orderId));
       orderItem.setProduct(createdOrderItem.getProduct());
        orderItem.setQuantity(createdOrderItem.getQuantity());
        orderItem.setPrice(createdOrderItem.getProduct().getPrice() * createdOrderItem.getQuantity());
        orderItemRepo.save(orderItem);
        return orderId;
    }


}





