package com.example.robwarehouse.service;

import com.example.robwarehouse.model.Product;
import com.example.robwarehouse.model.StockInOrder;
import com.example.robwarehouse.model.Supplier;
import com.example.robwarehouse.repository.OrderInRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

// jeigu liks laiko pridesiu!!!
@Service
@AllArgsConstructor
public class OrderInServiceImpl implements OrderInService {

    private final OrderInRepo orderInRepo;


    @Override
    public String createNewOrderIn(StockInOrder stockOrderIn, Product product, Supplier supplier){
        StockInOrder orderIn = new StockInOrder();
        orderIn.setGoodId(stockOrderIn.getGoodId());
        orderIn.setSupplier(supplier);
        orderIn.setGoodName(stockOrderIn.getGoodName());
        orderIn.getRecordInDate();
        orderInRepo.save(orderIn);

        return "redirect:/createOrderInForm";

    }
}
