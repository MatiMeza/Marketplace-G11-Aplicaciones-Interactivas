package com.uade.tpo.demo.entidades.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespuestaLogin {
    private String token;
    private Long id;
    private String email;
    private String nombre;
    private String telefono;
    private String direccion;
    private String rol; // "ROLE_ADMIN" o "ROLE_USER"
}