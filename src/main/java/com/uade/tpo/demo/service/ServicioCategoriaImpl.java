package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entidades.Categoria;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasCategoria;
import com.uade.tpo.demo.respositorios.RepositorioCategoria;

@Service
public class ServicioCategoriaImpl implements ServicioCategoria {
    @Autowired
    private RepositorioCategoria repositorioCategoria;



    public List<Categoria> getCategories() {
        return repositorioCategoria.findAll();
    }

    public Optional<Categoria> getCategoryById(Long categoryId) {
        return repositorioCategoria.findById(categoryId);
    }

    public Categoria createCategory(String description) throws ExcepcionesDuplicadasCategoria {
        List<Categoria> categories = repositorioCategoria.findAll();
        if (categories.stream().anyMatch(
                category -> category.getDescription().equals(description)))
            throw new ExcepcionesDuplicadasCategoria();
        return repositorioCategoria.save(new Categoria(description));
    }
}
