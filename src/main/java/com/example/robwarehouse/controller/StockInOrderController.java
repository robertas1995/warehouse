package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.Product;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

// jeigu liks laiko pridesiu!!!

@Controller
@Data
@Slf4j
public class StockInOrderController {

    @GetMapping("/in")
    public String createNewOrderIn(Model model){
        model.addAttribute("goods", new Product());
        return "createNewOrderIn";
    }

    @PostMapping("/in")
    public String createNewOrderIn(@Valid @ModelAttribute Product product){

        return "";
    }

}
