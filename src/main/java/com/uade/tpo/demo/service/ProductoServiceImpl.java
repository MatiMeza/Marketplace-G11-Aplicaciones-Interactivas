package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Producto;
import com.uade.tpo.demo.exceptions.ProductoDuplicateException;
import com.uade.tpo.demo.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto createProducto(Producto producto) throws ProductoDuplicateException {

        if (productoRepository.findByNombre(producto.getNombre()).isPresent()) {
            throw new ProductoDuplicateException();
        }
        
        return productoRepository.save(producto);
    }
}