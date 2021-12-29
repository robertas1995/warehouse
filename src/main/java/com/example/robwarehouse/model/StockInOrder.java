package com.example.robwarehouse.model;

import jdk.jfr.Timestamp;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


// jeigu liks laiko pridesiu!!!
@Entity
@Data
@Table(name = "stock_in_order")
public class StockInOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Supplier supplier;

    @Column(name = "good_id")
    private Long goodId;

    @Column(name = "good_name")
    private String goodName;

    @Timestamp
    @Column(name = "record_in_date")
    private LocalDate recordInDate;


}
