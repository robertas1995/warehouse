package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.*;
import com.example.robwarehouse.repository.ProductRepo;
import com.example.robwarehouse.repository.OrderItemRepo;
import com.example.robwarehouse.service.OrderSevice;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@Slf4j
@Controller
public class OrderController {

    private final OrderSevice orderSevice;
    private final OrderItemRepo orderItemRepo;
    private final ProductRepo productRepo;



    @GetMapping ("/createNewOrder")
    public String createNewOrder(Model model){
        Order order = new Order();
        model.addAttribute("order", order);
        List<Product> items = productRepo.findAll();
        model.addAttribute("items",items);
        return "orderForm";

    }
    @PostMapping("/createNewOrder")
         String addItem(
                 Model model,
                 @RequestParam String itemid,
                 @RequestParam Integer quantity,
                 @ModelAttribute(name = "order") Order order) {
        Long itemIdReal = Long.parseLong(itemid);
        order.getItems().add(new OrderItem(order, productRepo.getById(itemIdReal),quantity));
        model.addAttribute("order",order);
        List<Product>items = productRepo.findAll();
        model.addAttribute("items", items);
        return "orderForm";
    }

//    @PostMapping("/createNewOrder")
//    private String createNewOrder(
//            @Valid @ModelAttribute Order order,
//            @ModelAttribute(name = "itemList") List<OrderItem> orderItems,
//            Customer customer
//            Page<Item> items
//    ){
//         orderSevice.createNewOrder(order,customer,orderItems);
//        return "orderSuccess";
//    }


}
