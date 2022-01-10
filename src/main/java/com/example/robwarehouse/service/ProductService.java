package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface ProductService {


    String createNewGoods(Product createdProduct);

    void delete(Product product);


    void editGoods(Long productId, String productName, Double productPrice, String productDescription);

    Optional<Product> getById(Long id);

    Collection<Product> getAll();

}
