package com.example.robwarehouse.repository;

import com.example.robwarehouse.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Override
    Optional<Customer> findById(Long cLong);
}


