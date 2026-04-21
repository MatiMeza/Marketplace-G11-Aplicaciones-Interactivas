package com.uade.tpo.demo.respositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uade.tpo.demo.entidades.Categoria;


@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Long> {


    
}
