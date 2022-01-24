package com.example.robwarehouse.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OrderItemDto {
    private ArrayList<String> orderList;

    public ArrayList<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<String> orderList) {
        this.orderList = orderList;
    }
}
