package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Material;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasMateriales;

import java.util.List;
import java.util.Optional;

public interface ServicioMaterial {
    List<Material> getMaterials();
    Optional<Material> getMaterialById(Integer id);
    Material createMaterial(String name) throws ExcepcionesDuplicadasMateriales;
    Optional<Material> updateMaterial(Integer id, String name) throws ExcepcionesDuplicadasMateriales;
    boolean deleteMaterial(Integer id);
}