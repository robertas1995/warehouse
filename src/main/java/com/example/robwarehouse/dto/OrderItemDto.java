package com.example.robwarehouse.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderItemDto {
    private int quantity;
    private Long productId;

    public OrderItemDto() {}
}
