package com.example.robwarehouse.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "tel")
    private String tel;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;
}
