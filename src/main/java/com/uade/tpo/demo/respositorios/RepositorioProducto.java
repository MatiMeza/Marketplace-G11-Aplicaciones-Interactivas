package com.uade.tpo.demo.respositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.demo.entidades.Producto;
import java.util.Optional;

@Repository
public interface RepositorioProducto extends JpaRepository<Producto, Long> {
    Optional<Producto> findByNombre(String nombre);
}