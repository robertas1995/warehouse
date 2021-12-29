package com.example.robwarehouse.model;


import lombok.Data;

import javax.persistence.*;

// jeigu liks laiko pridesiu!!!
@Entity
@Data
@Table(name = "stock_out")
public class StockOut {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Column(name = "customer_id")
    private String customer;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "good_id")
    private Long goodId;

    @Column(name = "good_name")
    private String goodName;


}
