package com.uade.tpo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uade.tpo.demo.entity.Producto;
import com.uade.tpo.demo.service.ProductoService;
import com.uade.tpo.demo.exceptions.ProductoDuplicateException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
        return ResponseEntity.ok(productoService.getProductos());
    }

    @PostMapping
    public ResponseEntity<Object> createProducto(@RequestBody Producto producto) {
        try {
            Producto result = productoService.createProducto(producto);
            return ResponseEntity.created(URI.create("/productos/" + result.getId())).body(result);
        } catch (ProductoDuplicateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductoById(@PathVariable Integer id) {
        Optional<Producto> result = productoService.getProductoById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.noContent().build();
    }
}