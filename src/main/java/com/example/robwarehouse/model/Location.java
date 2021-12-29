package com.example.robwarehouse.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "location_name")
    private String locationName;

}
