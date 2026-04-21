package com.uade.tpo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entidades.Material;
import com.uade.tpo.demo.respositorios.RepositorioMaterial;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasMateriales;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioMaterialImpl implements ServicioMaterial {

    @Autowired
    private RepositorioMaterial repositorioMaterial;

    @Override
    public List<Material> getMaterials() {
        return repositorioMaterial.findAll();
    }

    @Override
    public Optional<Material> getMaterialById(Integer id) {
        return repositorioMaterial.findById(id);
    }

    @Override
    public Material createMaterial(String name) throws ExcepcionesDuplicadasMateriales {
        if (repositorioMaterial.findByName(name).isPresent()) {
            throw new ExcepcionesDuplicadasMateriales();
        }

        Material material = new Material();
        material.setName(name);
        return repositorioMaterial.save(material);
    }

    @Override
    public Optional<Material> updateMaterial(Integer id, String name) throws ExcepcionesDuplicadasMateriales {
        Optional<Material> materialOptional = repositorioMaterial.findById(id);

        if (materialOptional.isEmpty()) {
            return Optional.empty();
        }

        Optional<Material> materialConMismoNombre = repositorioMaterial.findByName(name);
        if (materialConMismoNombre.isPresent() && materialConMismoNombre.get().getId() != id) {
            throw new ExcepcionesDuplicadasMateriales();
        }

        Material materialExistente = materialOptional.get();
        materialExistente.setName(name);

        return Optional.of(repositorioMaterial.save(materialExistente));
    }

    @Override
    public boolean deleteMaterial(Integer id) {
        if (!repositorioMaterial.existsById(id)) {
            return false;
        }

        repositorioMaterial.deleteById(id);
        return true;
    }
}