package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Categoria;
import com.uade.tpo.demo.entidades.dto.SolicitudDeCategoría;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasCategoria;
import com.uade.tpo.demo.repositorios.RepositorioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

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
    public Categoria createCategory(SolicitudDeCategoría solicitud) throws ExcepcionesDuplicadasCategoria {
        if (repositorioCategoria.findByNombre(solicitud.getNombre()).isPresent()) {
            throw new ExcepcionesDuplicadasCategoria();
        }
        Categoria nuevaCategoria = new Categoria();

        
        nuevaCategoria.setNombre(solicitud.getNombre());
        nuevaCategoria.setSlug(solicitud.getSlug());
        nuevaCategoria.setDescripcion(solicitud.getDescripcion());
        nuevaCategoria.setPublicado(solicitud.isPublicado());

        return repositorioCategoria.save(nuevaCategoria);
    }

    @Override
    public Optional<Categoria> updateCategory(SolicitudDeCategoría solicitud) throws ExcepcionesDuplicadasCategoria {
        Optional<Categoria> categoriaOptional = repositorioCategoria.findById(solicitud.getId());

        if (categoriaOptional.isEmpty()) {
            return Optional.empty();
        }

        Optional<Categoria> categoriaConMismoNombre = repositorioCategoria.findByNombre(solicitud.getNombre());
        if (categoriaConMismoNombre.isPresent() && categoriaConMismoNombre.get().getId() != solicitud.getId()) {
            throw new ExcepcionesDuplicadasCategoria();
        }

        Categoria categoriaExistente = categoriaOptional.get();
        
        categoriaExistente.setNombre(solicitud.getNombre());
        categoriaExistente.setSlug(solicitud.getSlug());
        categoriaExistente.setDescripcion(solicitud.getDescripcion());
        categoriaExistente.setPublicado(solicitud.isPublicado());


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