package com.example.robwarehouse.service;

import com.example.robwarehouse.model.OrderItem;
import com.example.robwarehouse.model.Product;
import com.example.robwarehouse.model.StockInOrder;
import com.example.robwarehouse.model.Supplier;
import org.springframework.stereotype.Service;


// jeigu liks laiko pridesiu!!!
@Service
public interface OrderInService {


    String createNewOrderIn(StockInOrder stockOrderIn, Product product, Supplier supplier);

    void addOrderProcuts(OrderItem orderItem);
}
