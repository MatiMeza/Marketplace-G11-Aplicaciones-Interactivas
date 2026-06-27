package com.uade.tpo.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    @JsonIgnore
    private Pedido pedido;

    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre_producto")
    private String nombreProducto;

    @Column(name = "precio_unitario")
    private double precioUnitario;

    @Column(name = "cantidad")
    private int cantidad;

    public DetallePedido() {}
}