package com.example.robwarehouse.dto;

import com.example.robwarehouse.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {

    private Long productId;
    private Long cutomerId;



}
