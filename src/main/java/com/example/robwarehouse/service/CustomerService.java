package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface CustomerService {


    Customer createCustomer(Customer customer);

    Optional<Customer> getById(Long id);

    Collection<Customer> getAll();

    void editCustomer(Long id, String customerName, String customerLastname, String customerAddress, String customerEmail, String customerTel);

    void delete(Customer customer);
}
