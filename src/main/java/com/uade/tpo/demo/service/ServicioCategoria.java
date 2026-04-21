package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.demo.entidades.Categoria;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasCategoria;

public interface ServicioCategoria {
    public List<Categoria> getCategories();

    public Optional<Categoria> getCategoryById(Long categoryId);

    public Categoria createCategory(String description) throws ExcepcionesDuplicadasCategoria;
    
}
