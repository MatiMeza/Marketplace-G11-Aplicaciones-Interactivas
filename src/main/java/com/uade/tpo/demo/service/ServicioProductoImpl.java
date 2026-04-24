package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entidades.Producto;
import com.uade.tpo.demo.entidades.dto.SolicitudDeProducto;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasProducto;
import com.uade.tpo.demo.repositorios.RepositorioProducto;
import com.uade.tpo.demo.repositorios.RepositorioCategoria;
import com.uade.tpo.demo.repositorios.RepositorioUsuario;

@Service
public class ServicioProductoImpl implements ServicioProducto {

    @Autowired
    private RepositorioProducto repositorioProducto;

    @Autowired
    private RepositorioCategoria repositorioCategoria;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Override
    public List<Producto> getProductos() {
        return repositorioProducto.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Long id) {
        return repositorioProducto.findById(id);
    }

    @Override
    public Producto createProducto(SolicitudDeProducto req) throws ExcepcionesDuplicadasProducto {

        if (repositorioProducto.findByNombre(req.getNombre()).isPresent()) {
            throw new ExcepcionesDuplicadasProducto();
        }

        Producto nuevo = new Producto();
        nuevo.setNombre(req.getNombre());
        nuevo.setDescripcion(req.getDescripcion());
        nuevo.setPrecio(req.getPrecio());
        nuevo.setStock(req.getStock());
        nuevo.setTipo(req.getTipo());


        repositorioCategoria.findById(req.getIdCategoria())
            .ifPresent(nuevo::setCategoria);

        repositorioUsuario.findById(req.getIdVendedor())
            .ifPresent(nuevo::setVendedor);

        repositorioCategoria.findById(req.getIdCategoria()).ifPresent(nuevo::setCategoria);
        repositorioUsuario.findById(req.getIdVendedor()).ifPresent(nuevo::setVendedor);


        return repositorioProducto.save(nuevo);
    }

    @Override
    public Optional<Producto> updateProducto(Long id, SolicitudDeProducto req) throws ExcepcionesDuplicadasProducto {
        Optional<Producto> productoOptional = repositorioProducto.findById(id);

        if (productoOptional.isEmpty()) {
            return Optional.empty();
        }

        Optional<Producto> productoConMismoNombre = repositorioProducto.findByNombre(req.getNombre());
        if (productoConMismoNombre.isPresent() && !productoConMismoNombre.get().getId().equals(id)) {
            throw new ExcepcionesDuplicadasProducto();
        }

        Producto productoExistente = productoOptional.get();
        productoExistente.setNombre(req.getNombre());
        productoExistente.setDescripcion(req.getDescripcion());
        productoExistente.setPrecio(req.getPrecio());
        productoExistente.setStock(req.getStock());
        productoExistente.setTipo(req.getTipo());

        repositorioCategoria.findById(req.getIdCategoria()).ifPresent(productoExistente::setCategoria);
        repositorioUsuario.findById(req.getIdVendedor()).ifPresent(productoExistente::setVendedor);

        return Optional.of(repositorioProducto.save(productoExistente));
    }

    @Override
    public boolean deleteProducto(Long id) {
        if (!repositorioProducto.existsById(id)) {
            return false;
        }

        repositorioProducto.deleteById(id);
        return true;
    }
}