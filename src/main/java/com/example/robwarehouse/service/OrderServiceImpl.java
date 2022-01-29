package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Order;
import com.example.robwarehouse.model.OrderItem;
import com.example.robwarehouse.model.Product;
import com.example.robwarehouse.model.Status;
import com.example.robwarehouse.repository.OrderItemRepo;
import com.example.robwarehouse.repository.OrderRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderSevice {



    private final OrderRepo orderRepo;
    private final PositionService positionService;
    private final ProductService productService;
    private final OrderItemRepo orderItemRepo;


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
    public void addItem(OrderItem createdOrderItem, Long orderId) {
        Order order = orderRepo.getById(orderId);
        Product product = productService.get(createdOrderItem.getProduct().getId());

        createAndStoreNewItem(createdOrderItem, order, product);

        updateOrderTotalPrice(orderId, order);

        positionService.recalculatePositionsForProduct(product.getId(), createdOrderItem.getQuantity());
    }


    public void updateOrderTotalPrice(Long orderId, Order order) {
        List<OrderItem> allOrderItems = orderItemRepo.findByOrderId(orderId);
        Double allItems = allOrderItems.stream().map(OrderItem::getPrice).mapToDouble(Double::doubleValue).sum();
        order.setTotalPrice(allItems);
        orderRepo.save(order);
    }

    private OrderItem createAndStoreNewItem(OrderItem createdOrderItem, Order order, Product product) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(createdOrderItem.getQuantity());
        orderItem.setPrice(createdOrderItem.getProduct().getPrice() * (double) createdOrderItem.getQuantity());
        orderItemRepo.save(orderItem);
        return orderItem;
    }


    @Override
    public Collection<Order> getAll() {
        return orderRepo.findAll();
    }

    @Override
    public Long editOrder(Long id, Order editOrder) {
        Order order = orderRepo.getById(id);
        order.setStatus(editOrder.getStatus());
        order.setCreationDate(LocalDate.now());
        order.setEmployee(editOrder.getEmployee());
        order.setCustomer(editOrder.getCustomer());
        orderRepo.save(order);
        return order.getId();

    }


    @Override
    public Page<Order> getAllOrdersPageable(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "id"));
        return orderRepo.findAll(page);

    }

}





