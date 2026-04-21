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

    @Override
    public List<Categoria> getCategories() {
        return repositorioCategoria.findAll();
    }

    @Override
    public Optional<Categoria> getCategoryById(Long categoryId) {
        return repositorioCategoria.findById(categoryId);
    }

    @Override
    public Categoria createCategory(String description) throws ExcepcionesDuplicadasCategoria {
        if (repositorioCategoria.findByDescription(description).isPresent()) {
            throw new ExcepcionesDuplicadasCategoria();
        }

        return repositorioCategoria.save(new Categoria(description));
    }

    @Override
    public Optional<Categoria> updateCategory(Long categoryId, String description) throws ExcepcionesDuplicadasCategoria {
        Optional<Categoria> categoriaOptional = repositorioCategoria.findById(categoryId);

        if (categoriaOptional.isEmpty()) {
            return Optional.empty();
        }

        Optional<Categoria> categoriaConMismaDescripcion = repositorioCategoria.findByDescription(description);
        if (categoriaConMismaDescripcion.isPresent() && categoriaConMismaDescripcion.get().getId() != categoryId) {
            throw new ExcepcionesDuplicadasCategoria();
        }

        Categoria categoriaExistente = categoriaOptional.get();
        categoriaExistente.setDescription(description);

        return Optional.of(repositorioCategoria.save(categoriaExistente));
    }

    @Override
    public boolean deleteCategory(Long categoryId) {
        if (!repositorioCategoria.existsById(categoryId)) {
            return false;
        }

        repositorioCategoria.deleteById(categoryId);
        return true;
    }
}