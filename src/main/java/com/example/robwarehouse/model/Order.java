package com.example.robwarehouse.model;

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

    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @ManyToOne
    private Employee employee;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @Column(name = "total_price")
    private Double totalPrice;

    public Order(){

    }




}
