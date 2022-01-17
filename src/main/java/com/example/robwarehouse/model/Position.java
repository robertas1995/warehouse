package com.example.robwarehouse.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    private Location location;

    @OneToOne
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;



}



