package com.example.robwarehouse.service;

import com.example.robwarehouse.dto.PlaceOrderDto;
import com.example.robwarehouse.model.*;
import com.example.robwarehouse.repository.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderSevice{

    private final OrderRepo orderRepo;
    private final OrderItemService orderItemService;

    @Override
    public String createNewOrder(Order createdOrder) {
        return null;
    }

    @Override
    public String createNewOrder(Order createdOrder, Customer customer, List<OrderItem> orderItems ) {
        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setStatus(Status.Checking);
        newOrder.getCreationDate();
        for(OrderItem item: orderItems) {
//            OrderItem orderItem = new OrderItem();
//            orderItem.setProduct(item.getProduct());
//            orderItem.setQuantity(item.getQuantity());
//            orderItem.setOrder(newOrder);
        }
        orderRepo.save(newOrder);

        return null;
    }


}
