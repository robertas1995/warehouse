package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.Product;
import com.example.robwarehouse.service.ProductService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@Controller
@Data
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;


    @GetMapping("/createNewGoods")
    public String createNewProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "ProductsForm";

    }

    @PostMapping("/createNewGoods")
    public String createNewProduct(@Valid @ModelAttribute Product product) {
//TODO fix this return
        return productService.createNewGoods(product);
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = this.productService.getById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            model.addAttribute("product", product);
        }
        return "product";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        model.addAttribute("productId", id);
        var product = productService.getById(id);
        if (product.isPresent()){
            model.addAttribute("editProduct", product);
        }
        return "editProduct";
    }

    @PostMapping("/editProduct/{id}")
    public String editProduct(@PathVariable Long id,
                              @RequestParam(name = "productName") String productName,
                              @RequestParam(name = "productPrice") Double productPrice,
                              @RequestParam(name = "productDescription") String productDescription) {
        productService.editGoods(id, productName, productPrice, productDescription);


//TODO FIX URL products/{id}
        return "redirect:/products/product/" + id;
    }


    @GetMapping("/list")
    public String getAll(Model model) {

        Collection<Product> products = this.productService.getAll();
        model.addAttribute("products", products);

        return "productsList";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id) {
       //TODO move to service
        Optional<Product> optionalProduct = this.productService.getById(id);
        Product product = optionalProduct.get();
        this.productService.delete(product);
        return "redirect:/products/list";
    }


}
