package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Customer;

import java.util.Collection;
import java.util.Optional;


public interface CustomerService {


    Long createCustomer(Customer customer);

    Optional<Customer> getById(Long id);
//TODO get pages
    Collection<Customer> getAll();
//TODO fix this (Customer customer)
   void editCustomer(Long id, String customerName, String customerLastname, String customerAddress, String customerEmail, String customerTel);

    //TODO FIX this (Customer customer)


    //TODO DELETE BYID
    void delete(Customer customer);

}
