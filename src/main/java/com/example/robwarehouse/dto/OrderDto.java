package com.example.robwarehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class OrderDto {

    private Integer id;
    private @NotNull Integer userId;

    public OrderDto() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGetCustomerId() {
        return userId;
    }

    public void setCustomerId(Integer userId) {
        this.userId = userId;
    }

}
