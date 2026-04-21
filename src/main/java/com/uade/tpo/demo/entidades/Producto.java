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

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private double precio;

    @Column(nullable = false)
    private int stock;

    @Column
    private String tipo;

    //1. Relación con Categoría (muchos a uno)
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    //2. Relación con Vendedor (muchos a uno). El ID_vendedor se mapea a la entidad Usuario.
    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Usuario vendedor;

    //3. Relación con imagenes (uno a muchos)
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Imagen> imagenes;

    //4. Relación con materiales (muchos a muchos)
    @ManyToMany
    @JoinTable(
        name = "producto_material",
        joinColumns = @JoinColumn(name = "id_producto"),
        inverseJoinColumns = @JoinColumn(name = "id_material")
    )
    private List<Material> materiales;

    //5. Relación con descuentos (muchos a muchos)
    @ManyToMany
    @JoinTable(
        name = "producto_descuento",
        joinColumns = @JoinColumn(name = "id_producto"),
        inverseJoinColumns = @JoinColumn(name = "id_descuento")
    )
    private List<Descuento> descuentos;

    public Producto() {}
}