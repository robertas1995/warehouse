package com.example.robwarehouse.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    private Order order;

    @ManyToOne
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public OrderItem(Order order, Product product, Integer quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}
