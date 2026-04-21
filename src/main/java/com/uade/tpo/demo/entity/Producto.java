package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int stock;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Category categoria;

    @ManyToMany
    @JoinTable(
        name = "producto_material",
        joinColumns = @JoinColumn(name = "id_producto"),
        inverseJoinColumns = @JoinColumn(name = "id_material")
    )
    private List<Material> materiales;
}