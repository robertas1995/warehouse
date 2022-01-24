package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public interface ProductService extends CrudService<Product> {


    default Product newObject() {return new Product();}

    Long create(Product newObject);

    void update(Product update);

    Product get(Long id);

    void delete(Long id);

    Collection<Product> getAll();

}
