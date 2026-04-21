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
    private Long id; // Cambiado a Long para consistencia técnica

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

    // ESTO FALTABA: Relación con el vendedor según el DER
    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Usuario vendedor; 

    @ManyToMany
    @JoinTable(
        name = "producto_material",
        joinColumns = @JoinColumn(name = "id_producto"),
        inverseJoinColumns = @JoinColumn(name = "id_material")
    )
    private List<Material> materiales;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagen> imagenes;
}