package com.uade.tpo.demo.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.demo.entity.Material;

public class MaterialRepository {
    private ArrayList<Material> materiales;

    public MaterialRepository() {
        materiales = new ArrayList<Material>();
        materiales.add(Material.builder().id(1).name("Oro").build());
        materiales.add(Material.builder().id(2).name("Plata").build());
        materiales.add(Material.builder().id(3).name("Acero").build());
    }

    public ArrayList<Material> getMateriales() {
        return this.materiales;
    }

    public Material createMaterial(int newMaterialId, String name) {
        Material newMaterial = Material.builder()
                .id(newMaterialId)
                .name(name).build();

        this.materiales.add(newMaterial);
        return newMaterial;
    }

    public Optional<Material> getMaterialById(int id) {
        return this.materiales.stream().filter(m -> m.getId() == id).findFirst();
    }
    
}
