package com.uade.tpo.demo.entidades.dto;

import lombok.Data;
import java.util.List;

@Data
public class SolicitudPedido {
    private double total;
    private String direccionEnvio;
    private List<ItemPedido> productos;

    @Data
    public static class ItemPedido {
        private Long idProducto;
        private String nombreProducto;
        private double precioUnitario;
        private int cantidad;
    }
}