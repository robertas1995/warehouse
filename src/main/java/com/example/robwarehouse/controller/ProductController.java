package com.example.robwarehouse.controller;

import com.example.robwarehouse.model.Employee;
import com.example.robwarehouse.model.Product;
import com.example.robwarehouse.repository.ProductRepo;
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
public class ProductController {

    private final ProductService productService;
    private final ProductRepo productRepo;



    @GetMapping("/createNewGoods")
    public String createNewProduct(Model model){

        Product product = new Product();
        model.addAttribute("goods", product);

        return "goodsForm";

    }

    @PostMapping("/createNewGoods")
    public String createNewProduct(@Valid @ModelAttribute Product product){

        return productService.createNewGoods(product);
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        Optional<Product> optionalProduct = this.productService.getById(id);
        Product product = optionalProduct.get();
        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct (@PathVariable Long id, Model model){
        model.addAttribute("productId", id);
        var product = productRepo.getById(id);
        model.addAttribute("editProduct", product);
        return "/editProduct";
    }
    @PostMapping("/editProduct/{id}")
    public String editProduct(@PathVariable Long id, @RequestParam(name = "productName") String productName,
                              @RequestParam(name = "productPrice") Double productPrice,
                              @RequestParam(name = "productDescription") String productDescpirtion){
        productService.editGoods(id, productName, productPrice, productDescpirtion);
        return "redirect:/product/" + id;
    }

    @GetMapping("allProducts")
    public String getAll(Model model){

        Collection<Product> productCollection = this.productService.getAll();
        model.addAttribute("product",productCollection);

        return "allProducts";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id){
        Optional<Product> optionalProduct = this.productService.getById(id);
        Product product = optionalProduct.get();
        this.productService.delete(product);
        return "redirect:/product";
    }



}