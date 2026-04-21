package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import com.uade.tpo.demo.entidades.Material;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasMateriales;

public interface ServicioMaterial {
    List<Material> getMaterials();
    Optional<Material> getMaterialById(Integer id);
    Material createMaterial(String name) throws ExcepcionesDuplicadasMateriales;
}