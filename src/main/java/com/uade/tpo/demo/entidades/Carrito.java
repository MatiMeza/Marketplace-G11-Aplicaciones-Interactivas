package com.uade.tpo.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long idCarrito;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCarrito> detalles = new ArrayList<>();

    public void agregarProducto(Producto producto, int cantidad) {
        for (DetalleCarrito detalle : detalles) {
            if (detalle.getProducto().getId().equals(producto.getId())) {
                detalle.setCantidad(detalle.getCantidad() + cantidad);
                return;
            }
        }
        DetalleCarrito detalle = new DetalleCarrito();
        detalle.setCarrito(this);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalles.add(detalle);
    }

    public void actualizarCantidad(Long productoId, int cantidad) {
        if (cantidad <= 0) {
            detalles.removeIf(detalle -> detalle.getProducto().getId().equals(productoId));
            return;
        }
        for (DetalleCarrito detalle : detalles) {
            if (detalle.getProducto().getId().equals(productoId)) {
                detalle.setCantidad(cantidad);
                return;
            }
        }
    }

    public void eliminarProducto(Producto producto) {
        detalles.removeIf(detalle -> detalle.getProducto().getId().equals(producto.getId()));
    }

    public void eliminarProductoPorId(Long productoId) {
        detalles.removeIf(detalle -> detalle.getProducto().getId().equals(productoId));
    }

    public void vaciarCarrito() {
        detalles.clear();
    }

    public double calcularTotal() {
        return detalles.stream()
                .mapToDouble(DetalleCarrito::calcularSubtotal)
                .sum();
    }
}