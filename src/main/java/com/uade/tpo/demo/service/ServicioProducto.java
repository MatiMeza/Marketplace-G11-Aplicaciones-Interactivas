package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Producto;
import com.uade.tpo.demo.entidades.dto.SolicitudDeProducto;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasProducto;

import java.util.List;
import java.util.Optional;

public interface ServicioProducto {
    List<Producto> getProductos();
    Optional<Producto> getProductoById(Long id);
    Producto createProducto(SolicitudDeProducto solicitudDeProducto) throws ExcepcionesDuplicadasProducto;
    Optional<Producto> updateProducto(Long id, SolicitudDeProducto solicitudDeProducto) throws ExcepcionesDuplicadasProducto;
    boolean deleteProducto(Long id);
}