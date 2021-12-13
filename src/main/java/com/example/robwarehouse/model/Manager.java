package com.example.robwarehouse.model;

//Id, Name, LastName, StartDate
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Entity
@Table(name= "manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "manager_id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "'NAME can't be empty'")
    private String name;

    @Column(name = "lastname")
    @NotEmpty(message = "LASTNAME can't be empty")
    private String lastname;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false,updatable = false)
    private LocalDate startDate;



}
