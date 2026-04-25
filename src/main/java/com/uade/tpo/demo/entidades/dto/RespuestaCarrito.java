package com.uade.tpo.demo.entidades.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RespuestaCarrito {
    private Long idCarrito;
    private String emailUsuario;
    private List<ItemCarrito> productos;
    private double total;
}