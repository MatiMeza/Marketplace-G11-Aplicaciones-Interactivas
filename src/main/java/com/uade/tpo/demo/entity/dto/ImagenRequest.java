package com.uade.tpo.demo.entity.dto;

import lombok.Data;

@Data
public class ImagenRequest {
    private String url;
    private boolean esPrincipal;
    private int idProducto;
}