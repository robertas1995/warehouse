package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Customer;
import com.example.robwarehouse.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.awt.print.Pageable;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;


    @Override
    public Long create(Customer createdCustomer) {

        Customer customer = new Customer();
        customer.setName(createdCustomer.getName());
        customer.setAddress(createdCustomer.getAddress());
        customer.setEmail(createdCustomer.getEmail());
        customer.setTel(createdCustomer.getTel());
        customer.setLastname(createdCustomer.getLastname());
        customerRepo.save(customer);

        return customer.getId();
    }

    @Override
    public Customer get(Long id) {

        return customerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }




    @Override
    public Collection<Customer> getAll() {
        return customerRepo.findAll();
    }


    @Override
    public void update(Customer update) {
        Customer customer = customerRepo.findById(update.getId()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        customer.setName(update.getName());
        customer.setLastname(update.getLastname());
        customer.setAddress(update.getAddress());
        customer.setEmail(update.getEmail());
        customer.setTel(update.getTel());
        customerRepo.save(customer);


    }


    @Override
    public void delete(Long id) {
        customerRepo.deleteById(id);
    }


}
