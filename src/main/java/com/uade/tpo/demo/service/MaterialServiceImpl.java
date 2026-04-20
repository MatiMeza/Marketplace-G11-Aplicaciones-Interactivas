package com.uade.tpo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.demo.entity.Material;
import com.uade.tpo.demo.repository.MaterialRepository;
import com.uade.tpo.demo.exceptions.MaterialDuplicateException;
import java.util.List;
import java.util.Optional;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public List<Material> getMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Optional<Material> getMaterialById(Integer id) {
        return materialRepository.findById(id);
    }

    @Override
    public Material createMaterial(String name) throws MaterialDuplicateException {
        if (materialRepository.findByName(name).isPresent()) {
            throw new MaterialDuplicateException();
        }
        
        Material material = new Material();
        material.setName(name);
        return materialRepository.save(material);
    }
}