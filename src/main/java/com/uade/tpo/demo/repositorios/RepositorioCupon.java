package com.uade.tpo.demo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uade.tpo.demo.entidades.Cupon;
import java.util.Optional;

@Repository
public interface RepositorioCupon extends JpaRepository<Cupon, Long> {
    Optional<Cupon> findByCodigo(String codigo);
}