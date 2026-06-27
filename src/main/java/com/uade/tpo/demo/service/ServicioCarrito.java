package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Carrito;

public interface ServicioCarrito {
    Carrito obtenerCarrito(String email);
    Carrito agregarProducto(String email, Long productoId, int cantidad);
    Carrito vaciarCarrito(String email);
    void confirmarCompra(String email);  // NUEVO
}