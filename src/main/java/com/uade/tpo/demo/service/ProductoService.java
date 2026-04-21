package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Producto;
import com.uade.tpo.demo.entity.dto.ProductoRequest;
import com.uade.tpo.demo.exceptions.ProductoDuplicateException;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> getProductos();
    Optional<Producto> getProductoById(Long id);
    Producto createProducto(ProductoRequest productoRequest) throws ProductoDuplicateException;
}