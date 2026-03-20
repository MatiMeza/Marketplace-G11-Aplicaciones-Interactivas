package com.uade.tpo.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.Material;
import com.uade.tpo.demo.entity.dto.MaterialRequest;
import com.uade.tpo.demo.service.MaterialesService;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("materiales")
public class MaterialesController {

    private MaterialesService materialService;

    public MaterialesController() {
        materialService = new MaterialesService();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Material>> getMateriales() {
        return ResponseEntity.ok(materialService.getMaterials());
    }

    @PostMapping
    public ResponseEntity<Object> createMaterial(@RequestBody MaterialRequest materialRequest) {
        Material result = materialService.createMaterial(materialRequest.getId(), materialRequest.getName());
        return ResponseEntity.created(URI.create("/materiales/" + result.getId())).body(result);
    }

    @GetMapping("/{materialId}")
    public ResponseEntity<Object> getMaterialById(@PathVariable int materialId) {
        Optional<Material> result = materialService.getMaterialById(materialId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }
}