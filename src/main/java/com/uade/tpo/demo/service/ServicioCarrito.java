package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Carrito;

public interface ServicioCarrito {
    Carrito obtenerCarrito(String email);
    Carrito agregarProducto(String email, Long productoId, int cantidad);
    Carrito actualizarCantidadProducto(String email, Long productoId, int cantidad);
    Carrito eliminarProducto(String email, Long productoId);
    Carrito vaciarCarrito(String email);
    void confirmarCompra(String email);  // NUEVO
}