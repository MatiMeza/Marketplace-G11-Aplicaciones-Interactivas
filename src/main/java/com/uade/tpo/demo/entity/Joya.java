package com.uade.tpo.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Joya {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private double price;
    

     public Joya() {
    }

    public Joya(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
