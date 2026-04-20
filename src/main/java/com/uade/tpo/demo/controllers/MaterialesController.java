package com.uade.tpo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uade.tpo.demo.entity.Material;
import com.uade.tpo.demo.entity.dto.MaterialRequest;
import com.uade.tpo.demo.service.MaterialService;
import com.uade.tpo.demo.exceptions.MaterialDuplicateException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materiales")
public class MaterialesController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<Material>> getMateriales() {
        return ResponseEntity.ok(materialService.getMaterials());
    }

    @PostMapping
    public ResponseEntity<Object> createMaterial(@RequestBody MaterialRequest materialRequest) {
        try {
            Material result = materialService.createMaterial(materialRequest.getName());
            return ResponseEntity.created(URI.create("/materiales/" + result.getId())).body(result);
        } catch (MaterialDuplicateException e) {
            // Retorna 400 Bad Request si el material ya existe
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{materialId}")
    public ResponseEntity<Object> getMaterialById(@PathVariable int materialId) {
        Optional<Material> result = materialService.getMaterialById(materialId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        }
        return ResponseEntity.noContent().build();
    }
}