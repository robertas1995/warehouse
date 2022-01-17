package com.example.robwarehouse.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

//    @Column(name = "order_id")
//    private Long orderId;
//
//    @Column(name = "product_id")
//    private  Long productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;


    public OrderItem(Order orderId, Product productId, Double productPrice, Integer productQuantity) {
        this.price = productPrice;
        this.quantity = productQuantity;
    }
    public OrderItem(){

    }
}
