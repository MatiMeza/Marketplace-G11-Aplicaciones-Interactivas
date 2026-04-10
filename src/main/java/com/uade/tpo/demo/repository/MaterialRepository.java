package com.uade.tpo.demo.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.uade.tpo.demo.entity.Material;

@Repository
public class MaterialRepository {

    private ArrayList<Material> materiales;

    public MaterialRepository() {
        materiales = new ArrayList<>();

        Material m1 = new Material();
        m1.setId(1);
        m1.setName("Oro");

        Material m2 = new Material();
        m2.setId(2);
        m2.setName("Plata");

        Material m3 = new Material();
        m3.setId(3);
        m3.setName("Acero");

        materiales.add(m1);
        materiales.add(m2);
        materiales.add(m3);
    }

    public ArrayList<Material> getMateriales() {
        return this.materiales;
    }

    public Material createMaterial(int newMaterialId, String name) {
        Material newMaterial = new Material();
        newMaterial.setId(newMaterialId);
        newMaterial.setName(name);

        materiales.add(newMaterial);
        return newMaterial;
    }

    public Optional<Material> getMaterialById(int materialId) {
        return materiales.stream()
                .filter(m -> m.getId() == materialId)
                .findFirst();
    }
}