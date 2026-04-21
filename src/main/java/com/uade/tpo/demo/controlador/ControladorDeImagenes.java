package com.uade.tpo.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uade.tpo.demo.entidades.Imagen;
import com.uade.tpo.demo.service.ServicioImagen;
import java.util.List;

@RestController
@RequestMapping("/imagenes")
public class ControladorDeImagenes {

    @Autowired
    private ServicioImagen servicioImagen;

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Imagen>> getByProducto(@PathVariable int productoId) {
        return ResponseEntity.ok(servicioImagen.getImagenesByProducto(productoId));
    }

    @PostMapping
    public ResponseEntity<Imagen> createImagen(@RequestBody Imagen imagen) {
        return ResponseEntity.ok(servicioImagen.saveImagen(imagen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImagen(@PathVariable int id) {
        servicioImagen.deleteImagen(id);
        return ResponseEntity.noContent().build();
    }
}