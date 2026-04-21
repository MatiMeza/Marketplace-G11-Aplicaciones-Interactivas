package com.uade.tpo.demo.entidades.dto;

import lombok.Data;

@Data
public class SolicitudDeImagen {
    private String url;
    private boolean esPrincipal;
    private int idProducto;
}