package com.uade.tpo.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.uade.tpo.demo.entity.Material;
import com.uade.tpo.demo.entity.dto.MaterialRequest;
import com.uade.tpo.demo.service.MaterialesService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materiales")
public class MaterialesController {

    @Autowired
    private MaterialesService materialService;

    @GetMapping
    public ResponseEntity<List<Material>> getMateriales() {
        return ResponseEntity.ok(materialService.getMaterials());
    }

    @PostMapping
    public ResponseEntity<Object> createMaterial(@RequestBody MaterialRequest materialRequest) {
        Material result = materialService.createMaterial(materialRequest.getName());
        return ResponseEntity.created(URI.create("/materiales/" + result.getId())).body(result);
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