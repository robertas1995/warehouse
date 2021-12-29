package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.Customer;
import com.example.robwarehouse.repository.CustomerRepo;
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
@Data
@Controller

public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepo customerRepo;

    @GetMapping("/add")
    public String createCustomer(Model model){
        model.addAttribute("customer", new Customer());
      log.error("can't get tamplete");
        return "createCustomerForm";
    }

    @PostMapping("/add")
    public String createCustomer(@Valid @ModelAttribute Customer customer){


         customerService.createCustomer(customer);

         return "redirect:/customer/" + customer.getId();

    }

    @GetMapping("/customer/{id}")
    public String getCustomer(@PathVariable Long id, Model model){
        Optional<Customer> optionalCustomer = this.customerService.getById(id);
        if (optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            model.addAttribute("customer", customer);

        }
        return "customer";
    }

    @GetMapping("/customer/all")
    public String displayAllCustomer(Model model) {

        Collection<Customer>customers = this.customerService.getAll();
        model.addAttribute("customer", customers);

        return "customerList";
    }


    @GetMapping("/editCustomer/{id}")
    public String editCustomer(@PathVariable Long id, Model model){
        model.addAttribute("customerId", id);
        var customer =  customerRepo.getById(id);
        model.addAttribute("editCustomer", customer);
        return "/editCustomer";
    }

    @PostMapping("editCustomer/{id}")
    public String editCustomer(@PathVariable Long id,
                               @RequestParam(name = "customerName") String customerName,
                               @RequestParam(name = "customerAddress") String customerAddress,
                               @RequestParam(name = "customerEmail") String customerEmail,
                               @RequestParam(name = "customerLastname") String customerLastname,
                               @RequestParam(name = "customerTel") String customerTel){
        customerService.editCustomer(id, customerName, customerLastname, customerAddress, customerEmail, customerTel);
        return "redirect:/customer" + id;
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable Long id){
        Optional<Customer> optionalCustomer = this.customerService.getById(id);
        Customer customer = optionalCustomer.get();
        this.customerService.delete(customer);

        return "redirect:/customer";
    }










}
