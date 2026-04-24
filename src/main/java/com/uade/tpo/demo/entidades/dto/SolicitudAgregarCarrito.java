package com.uade.tpo.demo.entidades.dto;

import lombok.Data;

@Data
public class SolicitudAgregarCarrito {
    private Long productoId;
    private int cantidad;
}