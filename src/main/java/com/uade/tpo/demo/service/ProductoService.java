package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Producto;
import com.uade.tpo.demo.exceptions.ProductoDuplicateException;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> getProductos();
    Optional<Producto> getProductoById(Integer id);
    Producto createProducto(Producto producto) throws ProductoDuplicateException;
}