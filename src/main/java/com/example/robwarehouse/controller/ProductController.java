package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.Product;
import com.example.robwarehouse.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController extends AbstractCrudController<Product> {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        super("products", productService);
        this.productService = productService;

    }


}
