package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Product;
import com.example.robwarehouse.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;


    @Override
    public Long create(Product createdProduct) {

        Product newProduct = new Product();
        newProduct.setName(createdProduct.getName());
        newProduct.setPrice(createdProduct.getPrice());
        newProduct.setDescription(createdProduct.getDescription());
        productRepo.save(newProduct);

        return newProduct.getId();
    }

    @Override
    public void update(Product update) {
        var editProduct = productRepo.findById(update.getId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        editProduct.setPrice(update.getPrice());
        editProduct.setName(update.getName());
        editProduct.setDescription(update.getDescription());
        productRepo.save(editProduct);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }


    @Override
    public Product get(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public Collection<Product> getAll() {
        return productRepo.findAll();
    }
}
