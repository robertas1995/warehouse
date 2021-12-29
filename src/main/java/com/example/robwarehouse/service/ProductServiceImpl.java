package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Product;
import com.example.robwarehouse.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

        private final ProductRepo productRepo;


        @Override
        public String createNewGoods(Product createdProduct){

            Product newProduct = new Product();
            newProduct.setName(createdProduct.getName());
            newProduct.setPrice(createdProduct.getPrice());
            newProduct.setDescription(createdProduct.getDescription());
            productRepo.save(newProduct);

            return "redirect: /goods";
        }

        @Override
        public void delete(Product product){
            productRepo.delete(product);}



    @Override
        public void  editGoods(Long goodsId, String goodsName, Double goodsPrice, String goodsDescription){
            var goods = productRepo.findById(goodsId).orElseThrow(()-> new EntityNotFoundException("goods not found"));
            goods.setName(goodsName);
            goods.setPrice(goodsPrice);
            goods.setDescription(goodsDescription);
            productRepo.save(goods);
        }


        @Override
        public Optional<Product> getById(Long id){ return  productRepo.findById(id);}

        @Override
        public Collection<Product> getAll(){return productRepo.findAll();}
}
