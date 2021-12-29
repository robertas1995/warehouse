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

    @OneToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @OneToOne
    private Employee employee;

    @OneToMany
    private List<OrderItem> items = new ArrayList<>();

}
