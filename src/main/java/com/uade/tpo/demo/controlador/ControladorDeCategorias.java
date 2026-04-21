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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("categories")
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
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createCategory(@RequestBody SolicitudDeCategoría solicitudDeCategoría)
            throws ExcepcionesDuplicadasCategoria {
        Categoria result = servicioCategoria.createCategory(solicitudDeCategoría.getDescription());
        return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result);
    }

    
}
