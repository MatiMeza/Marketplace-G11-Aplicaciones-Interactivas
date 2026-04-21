package com.uade.tpo.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uade.tpo.demo.entidades.Producto;
import com.uade.tpo.demo.entidades.dto.SolicitudDeProducto;
import com.uade.tpo.demo.service.ServicioProducto;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasProducto;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ControladorDeProducto {

    @Autowired
    private ServicioProducto servicioProducto;

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
        return ResponseEntity.ok(servicioProducto.getProductos());
    }

    @PostMapping
    public ResponseEntity<Object> createProducto(@RequestBody SolicitudDeProducto solicitudDeProducto) {
        try {
            Producto result = servicioProducto.createProducto(solicitudDeProducto);
            return ResponseEntity.created(URI.create("/productos/" + result.getId())).body(result);
        } catch (ExcepcionesDuplicadasProducto e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductoById(@PathVariable Long id) { // Cambiado a Long
        Optional<Producto> result = servicioProducto.getProductoById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.noContent().build();
    }
}