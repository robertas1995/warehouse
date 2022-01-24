package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.*;
import com.example.robwarehouse.repository.*;
import com.example.robwarehouse.service.OrderItemService;
import com.example.robwarehouse.service.OrderSevice;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Data
@Slf4j
@Controller
public class OrderController {

    private final OrderSevice orderSevice;
    private final OrderItemRepo orderItemRepo;
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;
    private final EmployeeRepo employeeRepo;
    private final OrderItemService orderItemService;


    @GetMapping("/createNewOrder")
    public String createNewOrder(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        List<Product> product = productRepo.findAll();
        model.addAttribute("products", product);
        List<Employee> employees = employeeRepo.findAll();
        model.addAttribute("employees", employees);
        List<Customer> customers = customerRepo.findAll();
        model.addAttribute("customers", customers);
        OrderItem orderItem = new OrderItem();
        model.addAttribute("orderItem", orderItem);

        return "orderForm";

    }

    @GetMapping("/createdOrder/{id}/addItem/")
    public String addItems(
            @PathVariable Long id,
            @ModelAttribute OrderItem orderItem, Model model) {
        Order order = orderSevice.getById(id).orElseThrow();
        model.addAttribute("order", order);
        OrderItem orderItems = new OrderItem();
        model.addAttribute("orderItem", orderItems);
        List<Product> product = productRepo.findAll();
        model.addAttribute("products", product);
        return "addItem";
    }

    @GetMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable Long id) {

        Optional<OrderItem> optionalOrderItem = this.orderItemService.getById(id);
        OrderItem orderItem = optionalOrderItem.get();
        this.orderItemService.delete(orderItem);
        return "redirect:/orderList";
    }

    @GetMapping("/editItem/{id}")
    public String editItem(@PathVariable Long id, Model model) {
        model.addAttribute("orderItemId", id);
        var orderItem = orderItemService.getById(id);
        if (orderItem.isPresent()) {
            model.addAttribute("editItem", orderItem.get());
            List<Product> product = productRepo.findAll();
            model.addAttribute("products", product);
        }
        return "editItem";
    }
    //TODO need to fix this

    @PostMapping("/editItem/{id}")
    public String editItem(@PathVariable Long id,
                           @ModelAttribute OrderItem orderItem) {
        orderItemService.editItem(id, orderItem);

        return "redirect:/orderList";
    }

    @PostMapping("/createdOrder/{id}/addItem/")
    public String addItem(@PathVariable Long id,
                          @ModelAttribute(name = "order") Order order,
                          @ModelAttribute(name = "orderItem") OrderItem orderItem,
                          @ModelAttribute(name = "product") Long productId) {

        orderSevice.addItem(orderItem, id, order);
        return "redirect:/createdOrder/" + id;
    }


    @PostMapping("/createNewOrder")
    String createNewOrder(@Valid @ModelAttribute Order order) {


        return "redirect:/createdOrder/" + orderSevice.createNewOrder(order);
    }


    @GetMapping("/createdOrder/{id}")
    public String createdOrder(@PathVariable Long id, Model model) {
        Optional<Order> createdOrder = this.orderSevice.getById(id);
        if (createdOrder.isPresent()) {
            Order order = createdOrder.get();
            model.addAttribute("order", order);
            var items = orderItemRepo.findByOrderId(id);
            model.addAttribute("items", items);
            Set<Status> allStatus = EnumSet.allOf(Status.class);
            model.addAttribute("status", allStatus);
            return "singleOrder";
        } else
            return "redirect:/";
    }

    @GetMapping("/orderList")
    public String getAll(Model model) {
        Collection<Order> orders = this.orderSevice.getAll();
        model.addAttribute("orders", orders);
        return "orderList";
    }


    @PatchMapping("/createdOrder/{id}/")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id,
                                              @Valid @RequestBody Order orderDetails) {
        Order order = orderRepo.getById(id);

        order.setStatus(orderDetails.getStatus());
        final Order updatedOrder = orderRepo.save(order);
        return ResponseEntity.ok(updatedOrder);
    }


}

