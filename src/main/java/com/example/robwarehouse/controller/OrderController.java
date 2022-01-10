package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.*;
import com.example.robwarehouse.repository.CustomerRepo;
import com.example.robwarehouse.repository.EmployeeRepo;
import com.example.robwarehouse.repository.ProductRepo;
import com.example.robwarehouse.repository.OrderItemRepo;
import com.example.robwarehouse.service.OrderSevice;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Data
@Slf4j
@Controller
public class OrderController {

    private final OrderSevice orderSevice;
    private final OrderItemRepo orderItemRepo;
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;
    private final EmployeeRepo employeeRepo;

    @GetMapping ("/createNewOrder")
    public String createNewOrder(Model model){
        Order order = new Order();
        model.addAttribute("order", order);
        List<Product> items = productRepo.findAll();
        model.addAttribute("items",items);
        List<Employee> employees = employeeRepo.findAll();
        model.addAttribute("employees", employees);
        List<Customer> customers = customerRepo.findAll();
        model.addAttribute("customer",customers);


        return "orderForm";

    }
    @PostMapping ("/createNewOrder")
    String addItem(@Valid @ModelAttribute Order order){

        return orderSevice.addItem(order);
    }








    @GetMapping("/createdOrder")
    public String createdOrder(Long id, Model model){
        Optional<Order> newOrder = this.orderSevice.getById(id);
        if (newOrder.isPresent()){
            Order order = newOrder.get();
            model.addAttribute("order",order);
        }
        return "singleOrder";
    }


    }

