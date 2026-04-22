package com.uade.tpo.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.demo.entidades.Imagen;
import com.uade.tpo.demo.service.ServicioImagen;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imagenes")
public class ControladorDeImagenes {

    @Autowired
    private ServicioImagen servicioImagen;

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Imagen>> getByProducto(@PathVariable int productoId) {
        return ResponseEntity.ok(servicioImagen.getImagenesByProducto(productoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> getById(@PathVariable int id) {
        Optional<Imagen> imagen = servicioImagen.getImagenById(id);

        if (imagen.isPresent()) {
            return ResponseEntity.ok(imagen.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Imagen> createImagen(@RequestBody Imagen imagen) {
        Imagen nueva = servicioImagen.saveImagen(imagen);
        return ResponseEntity.created(URI.create("/imagenes/" + nueva.getId())).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imagen> updateImagen(@PathVariable int id, @RequestBody Imagen imagen) {
        Optional<Imagen> existente = servicioImagen.getImagenById(id);

        if (existente.isPresent()) {
            Imagen img = existente.get();
            img.setUrl(imagen.getUrl());
            img.setEsPrincipal(imagen.isEsPrincipal());
            img.setProducto(imagen.getProducto());

            return ResponseEntity.ok(servicioImagen.saveImagen(img));
        }

        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImagen(@PathVariable int id) {
        servicioImagen.deleteImagen(id);
        return ResponseEntity.noContent().build();
    }
}