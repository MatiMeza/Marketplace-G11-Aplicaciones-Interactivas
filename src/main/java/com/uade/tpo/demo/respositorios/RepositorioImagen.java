package com.uade.tpo.demo.respositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.demo.entidades.Imagen;
import java.util.List;

@Repository
public interface RepositorioImagen extends JpaRepository<Imagen, Integer> {
    List<Imagen> findByProductoId(int productoId);
}