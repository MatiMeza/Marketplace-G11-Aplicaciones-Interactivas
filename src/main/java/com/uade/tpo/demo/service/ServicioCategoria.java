package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Categoria;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasCategoria;

import java.util.List;
import java.util.Optional;

public interface ServicioCategoria {
    List<Categoria> getCategories();
    Optional<Categoria> getCategoryById(Long categoryId);
    Categoria createCategory(String description) throws ExcepcionesDuplicadasCategoria;
    Optional<Categoria> updateCategory(Long categoryId, String description) throws ExcepcionesDuplicadasCategoria;
    boolean deleteCategory(Long categoryId);
}