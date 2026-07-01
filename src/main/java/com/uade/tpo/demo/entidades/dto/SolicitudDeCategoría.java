package com.uade.tpo.demo.entidades.dto;

import lombok.Data;

@Data
public class SolicitudDeCategoría {   

    private Long id; 
    private String nombre;
    private String slug;
    private String descripcion;
    private boolean publicado;
}