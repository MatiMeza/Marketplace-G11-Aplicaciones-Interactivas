package com.uade.tpo.demo.entidades.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemCarrito {
    private Long idProducto;
    private String nombre;
    private double precio;
    private int cantidad;
    private double subtotal;
}