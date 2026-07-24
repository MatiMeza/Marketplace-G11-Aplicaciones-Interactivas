package com.uade.tpo.demo.entidades.dto;

import lombok.Data;
import java.util.List;

@Data
public class SolicitudPedido {
    private String direccionEnvio;
    private String emailContacto;
    private String cuponAplicado;
    private List<ItemPedido> productos;

    @Data
    public static class ItemPedido {
        private Long idProducto;
        private int cantidad;
    }
}