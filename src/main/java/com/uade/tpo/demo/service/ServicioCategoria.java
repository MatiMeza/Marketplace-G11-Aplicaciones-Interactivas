package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Categoria;
import com.uade.tpo.demo.entidades.dto.SolicitudDeCategoría;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasCategoria;

import java.util.List;
import java.util.Optional;

public interface ServicioCategoria {
    List<Categoria> getCategories();
    Optional<Categoria> getCategoryById(Long categoryId);
    Categoria createCategory(SolicitudDeCategoría solicitud) throws ExcepcionesDuplicadasCategoria;
    Optional<Categoria> updateCategory(SolicitudDeCategoría solicitud) throws ExcepcionesDuplicadasCategoria;
    boolean deleteCategory(Long categoryId);
}