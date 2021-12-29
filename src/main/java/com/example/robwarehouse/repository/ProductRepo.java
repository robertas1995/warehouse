package com.example.robwarehouse.repository;
import com.example.robwarehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepo extends JpaRepository <Product, Long> {


}
