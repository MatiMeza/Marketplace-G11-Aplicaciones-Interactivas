package com.uade.tpo.demo.entidades.dto;

import lombok.Data;
import java.util.List;

@Data
public class SolicitudPedido {
    // Se elimina el atributo 'total'
    private String direccionEnvio;
    private String emailContacto;
    private String cuponAplicado;
    private List<ItemPedido> productos;

    @Data
    public static class ItemPedido {
        private Long idProducto;
        private int cantidad;
        // Se eliminan precioUnitario y nombreProducto. Se obtienen de la BD.
    }
}