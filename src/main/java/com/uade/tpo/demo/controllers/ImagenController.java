package com.uade.tpo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uade.tpo.demo.entity.Imagen;
import com.uade.tpo.demo.service.ImagenService;
import java.util.List;

@RestController
@RequestMapping("/imagenes")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Imagen>> getByProducto(@PathVariable int productoId) {
        return ResponseEntity.ok(imagenService.getImagenesByProducto(productoId));
    }

    @PostMapping
    public ResponseEntity<Imagen> createImagen(@RequestBody Imagen imagen) {
        return ResponseEntity.ok(imagenService.saveImagen(imagen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImagen(@PathVariable int id) {
        imagenService.deleteImagen(id);
        return ResponseEntity.noContent().build();
    }
}