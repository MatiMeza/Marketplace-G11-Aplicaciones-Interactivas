package com.uade.tpo.demo.respositorios;

import com.uade.tpo.demo.entidades.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioCarrito extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByUsuarioEmail(String email);
}