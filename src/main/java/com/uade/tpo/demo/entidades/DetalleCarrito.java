package com.uade.tpo.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_carrito")
public class DetalleCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private int cantidad;

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}