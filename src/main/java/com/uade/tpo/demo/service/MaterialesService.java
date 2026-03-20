package com.uade.tpo.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.demo.entity.Material;
import com.uade.tpo.demo.repository.MaterialRepository;

public class MaterialesService {

    private MaterialRepository materialRepository;

    public MaterialesService() {
        materialRepository = new MaterialRepository();
    }

    public ArrayList<Material> getMaterials() {
        return materialRepository.getMateriales();
    }

    public Optional<Material> getMaterialById(int id) {
        return materialRepository.getMaterialById(id);
    }

    public Material createMaterial(int id, String name) {
        return materialRepository.createMaterial(id, name);
    }
}