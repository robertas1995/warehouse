package com.example.robwarehouse.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlaceOrderDto {

    private Integer id;
    private @NotNull Integer userId;
    private @NotNull Double totalPrice;

    public PlaceOrderDto() {
    }
}
