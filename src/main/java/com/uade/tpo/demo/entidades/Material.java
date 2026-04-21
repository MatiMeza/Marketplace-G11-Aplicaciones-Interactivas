package com.uade.tpo.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "materiales")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;


}