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
    private Categoria categoria; //Joyeria lingotes etc

    @Column(name = "subcategoria")
    private String subcategoria; // Aros anillos etc

    // --- Campos de contenido para la ficha de detalle (antes vivían solo en el mock del front) ---
    @Column
    private String badge; // ej: "EXCLUSIVIDAD" — null si no tiene

    @Column
    private String peso; // texto libre, ej: "12 gramos"

    @Column
    private String certificacion; // ej: "Autenticado por GIA"

    @Column(name = "composicion_material", columnDefinition = "TEXT")
    private String composicionMaterial; // descripción larga del material (distinta del Material de filtrado)

    @Column(columnDefinition = "TEXT")
    private String esencia; // texto largo de "La Esencia de la Elegancia"

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