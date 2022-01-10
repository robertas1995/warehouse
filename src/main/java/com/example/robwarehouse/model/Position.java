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
   // @JoinColumn(name = "location_id",referencedColumnName = "id")
    private Location location;

    @OneToOne
    //@JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;



}



