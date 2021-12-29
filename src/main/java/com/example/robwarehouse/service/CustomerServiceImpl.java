package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Customer;
import com.example.robwarehouse.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepo customerRepo;


    @Override
    public Customer createCustomer(Customer createdCustomer){
//        Customer customer = new Customer();
//        customer.setName(createdCustomer.getName());
//        customer.setAddress(createdCustomer.getAddress());
//        customer.setEmail(createdCustomer.getEmail());
//        customer.setTel(createdCustomer.getTel());
//        customer.setLastname(createdCustomer.getLastname());
        customerRepo.save(createdCustomer);



        return createdCustomer;
    }

    @Override
    public Optional<Customer> getById(Long id) {
        return customerRepo.findById(id);
    }

    @Override
    public Collection<Customer> getAll() {
        return customerRepo.findAll();
    }
    @Override
    public void editCustomer(Long id, String customerName, String customerLastname, String customerAddress, String customerEmail, String customerTel){
        var customer = customerRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("customer not found"));
        customer.setName(customerName);
        customer.setLastname(customerLastname);
        customer.setAddress(customerAddress);
        customer.setEmail(customerEmail);
        customer.setTel(customerTel);
        customerRepo.save(customer);
    }
    @Override
    public void delete(Customer customer){customerRepo.delete(customer);}


}
