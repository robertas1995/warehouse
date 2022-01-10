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

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private @NotNull Long productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private @NotNull double price;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id", insertable = false,updatable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Product product;

    public OrderItem(){}

    public OrderItem(Long orderId, Long productId,int quantity, double price){
        this.orderId = productId;
        this.productId = productId;
        this.price = price;
        this.orderId = orderId;


    }


}
