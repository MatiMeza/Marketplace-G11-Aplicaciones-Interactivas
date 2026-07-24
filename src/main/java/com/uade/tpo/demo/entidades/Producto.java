package com.uade.tpo.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int stock;

    @Column
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @Column(name = "subcategoria")
    private String subcategoria;

    @Column
    private String badge;

    @Column
    private String peso;

    @Column
    private String certificacion;

    @Column(name = "composicion_material", columnDefinition = "TEXT")
    private String composicionMaterial;

    @Column(columnDefinition = "TEXT")
    private String esencia;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Caracteristica> caracteristicas;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Usuario vendedor;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Imagen> imagenes;

    @ManyToMany
    @JoinTable(
            name = "producto_material",
            joinColumns = @JoinColumn(name = "id_producto"),
            inverseJoinColumns = @JoinColumn(name = "id_material")
    )
    private List<Material> materiales;

    @ManyToMany
    @JoinTable(
            name = "producto_descuento",
            joinColumns = @JoinColumn(name = "id_producto"),
            inverseJoinColumns = @JoinColumn(name = "id_descuento")
    )
    private List<Descuento> descuentos;

    public Producto() {}
}