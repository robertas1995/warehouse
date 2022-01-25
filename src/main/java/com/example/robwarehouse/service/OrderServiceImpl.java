package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Order;
import com.example.robwarehouse.model.OrderItem;
import com.example.robwarehouse.model.Position;
import com.example.robwarehouse.model.Status;
import com.example.robwarehouse.repository.OrderItemRepo;
import com.example.robwarehouse.repository.OrderRepo;
import com.example.robwarehouse.repository.PositionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderSevice {

    private final OrderRepo orderRepo;
    private final PositionService positionService;
    private final OrderItemRepo orderItemRepo;
    private final PositionRepo positionRepo;



    @Override
    public Long createNewOrder(Order newOrder) {
        Order order = new Order();
        order.setCustomer(newOrder.getCustomer());
        order.setStatus(Status.Checking);
        order.getCreationDate();
        order.setEmployee(newOrder.getEmployee());
        order.setTotalPrice(newOrder.getTotalPrice());
//
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
    @Transactional
    public Long addItem(OrderItem createdOrderItem, Long orderId, Order order) {


        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(orderRepo.getById(orderId));
        orderItem.setProduct(createdOrderItem.getProduct());
        orderItem.setQuantity(createdOrderItem.getQuantity());
        orderItem.setPrice(createdOrderItem.getProduct().getPrice() * createdOrderItem.getQuantity());
        orderItemRepo.save(orderItem);
        Double allItems = orderItemRepo.findByOrderId(orderId).stream()
                .map(x -> x.getPrice())
                .collect(Collectors.summingDouble(Double::doubleValue));
        order.setTotalPrice(allItems);
        orderRepo.getById(orderId).setTotalPrice(allItems);
       //TODO finish this
        Integer minusProduct = orderItem.getQuantity();
        Integer storageItem = positionRepo.getById(orderItem.getProduct().getId()).getQuantity();
        Integer result = storageItem - minusProduct;
        if (storageItem < minusProduct){
            System.out.println("Storage is not enough");
        }else
            positionRepo.getById(orderItem.getProduct().getId()).setQuantity(result);






        return orderId;
    }

    @Override
    public Collection<Order> getAll() {
        return orderRepo.findAll();
    }


}





