package com.example.robwarehouse.model;

import lombok.Data;

import javax.persistence.*;

// jeigu liks laiko pridesiu!!!
@Entity
@Data
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "supplier_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

}
