package com.uade.tpo.demo.respositorios;

import com.uade.tpo.demo.entidades.DetalleCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDetalleCarrito extends JpaRepository<DetalleCarrito, Long> {
}