package com.example.robwarehouse.repository;

import com.example.robwarehouse.model.StockInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

// jeigu liks laiko pridesiu!!!
public interface OrderInRepo extends JpaRepository <StockInOrder, Long> {

}
