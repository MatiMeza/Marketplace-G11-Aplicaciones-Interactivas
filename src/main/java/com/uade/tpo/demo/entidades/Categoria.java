package com.uade.tpo.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String nombre; 

    @Column(unique = true)
    private String slug;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private boolean publicado = true;


    public Categoria() {}
}