package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.Customer;
import com.example.robwarehouse.service.CustomerService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/customers")
public class CustomerController extends AbstractCrudController<Customer> {

    private final CustomerService customerService;

   public CustomerController(CustomerService customerService){
       super("customers",customerService);
       this.customerService = customerService;
   }

}
