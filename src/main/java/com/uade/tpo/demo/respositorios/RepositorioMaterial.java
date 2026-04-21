package com.uade.tpo.demo.respositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.demo.entidades.Material;
import java.util.Optional;

@Repository
public interface RepositorioMaterial extends JpaRepository<Material, Integer> {
    Optional<Material> findByName(String name);
}