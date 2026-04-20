package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;
import com.uade.tpo.demo.entity.Material;
import com.uade.tpo.demo.exceptions.MaterialDuplicateException;

public interface MaterialService {
    List<Material> getMaterials();
    Optional<Material> getMaterialById(Integer id);
    Material createMaterial(String name) throws MaterialDuplicateException;
}