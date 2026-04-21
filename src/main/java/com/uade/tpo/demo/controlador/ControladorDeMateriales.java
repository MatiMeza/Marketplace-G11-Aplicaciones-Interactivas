package com.uade.tpo.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uade.tpo.demo.entidades.Material;
import com.uade.tpo.demo.entidades.dto.SolicitudDeMaterial;
import com.uade.tpo.demo.service.ServicioMaterial;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasMateriales;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materiales")
public class ControladorDeMateriales {

    @Autowired
    private ServicioMaterial servicioMaterial;

    @GetMapping
    public ResponseEntity<List<Material>> getMateriales() {
        return ResponseEntity.ok(servicioMaterial.getMaterials());
    }

    @PostMapping
    public ResponseEntity<Object> createMaterial(@RequestBody SolicitudDeMaterial solicitudDeMaterial) {
        try {
            Material result = servicioMaterial.createMaterial(solicitudDeMaterial.getName());
            return ResponseEntity.created(URI.create("/materiales/" + result.getId())).body(result);
        } catch (ExcepcionesDuplicadasMateriales e) {
            // Retorna 400 Bad Request si el material ya existe
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{materialId}")
    public ResponseEntity<Object> getMaterialById(@PathVariable int materialId) {
        Optional<Material> result = servicioMaterial.getMaterialById(materialId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.noContent().build();
    }
}