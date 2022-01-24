package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Customer;

import java.util.Collection;


public interface CustomerService extends CrudService<Customer> {

    default Customer newObject() {
        return new Customer();
    }

    Long create(Customer customer);

    void update(Customer update);

    Customer get(Long id);

    void delete(Long id);

    Collection<Customer> getAll();
}
