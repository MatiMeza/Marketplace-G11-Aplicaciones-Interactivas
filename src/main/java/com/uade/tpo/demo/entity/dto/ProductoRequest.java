package com.uade.tpo.demo.entity.dto;

import lombok.Data;

@Data
public class ProductoRequest {
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String tipo;
    private Long idCategoria; 
    private Long idVendedor;
}