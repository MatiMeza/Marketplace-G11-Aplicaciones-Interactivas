package com.uade.tpo.demo.controlador;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entidades.Categoria;
import com.uade.tpo.demo.entidades.dto.SolicitudDeCategoría;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasCategoria;
import com.uade.tpo.demo.service.ServicioCategoria;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class ControladorDeCategorias {

    @Autowired
    private ServicioCategoria servicioCategoria;

    @GetMapping
    public ResponseEntity<List<Categoria>> getCategories() {
        return ResponseEntity.ok(servicioCategoria.getCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Categoria> getCategoryById(@PathVariable Long categoryId) {
        Optional<Categoria> result = servicioCategoria.getCategoryById(categoryId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody SolicitudDeCategoría solicitudDeCategoría)
            throws ExcepcionesDuplicadasCategoria {
        Categoria result = servicioCategoria.createCategory(solicitudDeCategoría.getDescription());
        return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long categoryId,
                                                 @RequestBody SolicitudDeCategoría solicitudDeCategoría) {
        try {
            Optional<Categoria> categoriaActualizada = servicioCategoria.updateCategory(
                    categoryId,
                    solicitudDeCategoría.getDescription()
            );

            if (categoriaActualizada.isPresent()) {
                return ResponseEntity.ok(categoriaActualizada.get());
            }

            return ResponseEntity.notFound().build();
        } catch (ExcepcionesDuplicadasCategoria e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        boolean eliminada = servicioCategoria.deleteCategory(categoryId);

        if (eliminada) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}