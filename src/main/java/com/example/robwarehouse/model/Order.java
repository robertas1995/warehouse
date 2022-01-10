package com.example.robwarehouse.model;

import com.example.robwarehouse.dto.PlaceOrderDto;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id",nullable = false,updatable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id",nullable = false,updatable = false)
    private Employee employee;
//TODO manau cia kazkas negerai
    @OneToMany(mappedBy = "order",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER )
    private List<OrderItem> orderItems;

    @Column(name = "total_price")
    private Double totalPrice;

    public Order(){

    }

    public Order(PlaceOrderDto orderDto, Customer customer){
        this.totalPrice = orderDto.getTotalPrice();
        this.customer = customer;
    }



}
