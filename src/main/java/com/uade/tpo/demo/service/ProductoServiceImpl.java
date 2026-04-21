package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Producto;
import com.uade.tpo.demo.entity.dto.ProductoRequest;
import com.uade.tpo.demo.exceptions.ProductoDuplicateException;
import com.uade.tpo.demo.repository.ProductoRepository;
import com.uade.tpo.demo.repository.CategoryRepository;
import com.uade.tpo.demo.repository.UsuarioRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto createProducto(ProductoRequest req) throws ProductoDuplicateException {
        
        if (productoRepository.findByNombre(req.getNombre()).isPresent()) {
            throw new ProductoDuplicateException();
        }

        Producto nuevo = new Producto();
        nuevo.setNombre(req.getNombre());
        nuevo.setDescripcion(req.getDescripcion());
        nuevo.setPrecio(req.getPrecio());
        nuevo.setStock(req.getStock());
        nuevo.setTipo(req.getTipo());

        categoryRepository.findById(req.getIdCategoria())
            .ifPresent(nuevo::setCategoria);

        usuarioRepository.findById(req.getIdVendedor())
            .ifPresent(nuevo::setVendedor);

        return productoRepository.save(nuevo);
    }
}