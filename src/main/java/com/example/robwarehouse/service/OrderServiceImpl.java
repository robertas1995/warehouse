package com.example.robwarehouse.service;

import com.example.robwarehouse.model.*;
import com.example.robwarehouse.repository.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderSevice{

    private final OrderRepo orderRepo;
    private final OrderItemService orderItemService;


    @Override
    public Optional<Order> createNewOrder(Order createdOrder, Customer customer, List<OrderItem> orderItems ) {


        Order newOrderAdd = new Order();
        newOrderAdd.setCustomer(customer);
        newOrderAdd.setStatus(Status.Checking);
        newOrderAdd.getCreationDate();
        for(OrderItem item: orderItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(item.getId());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setOrder(newOrderAdd);

        }
        orderRepo.save(newOrderAdd);
        System.out.println("item was added");

        return  createNewOrder(createdOrder,customer,orderItems);
    }

    @Override
        public Optional<Order>getById(Long id){return orderRepo.findById(id);}


@Override
    public String addItem(Order createdOrder){
        Order order = new Order();
        order.setOrderItems(createdOrder.getOrderItems());
        order.setCreationDate(LocalDate.now());
        order.setStatus(Status.Checking);
        order.setCustomer(createdOrder.getCustomer());
        order.setEmployee(createdOrder.getEmployee());
        orderRepo.save(order);
        return "singleOrder";
}

    }



