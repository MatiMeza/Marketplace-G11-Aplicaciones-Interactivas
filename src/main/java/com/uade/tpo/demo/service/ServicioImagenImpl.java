package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Imagen;
import com.uade.tpo.demo.repositorios.RepositorioImagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioImagenImpl implements ServicioImagen {

    @Autowired
    private RepositorioImagen repositorioImagen;

    @Override
    public Imagen saveImagen(Imagen imagen) {
        return repositorioImagen.save(imagen);
    }

    @Override
    public List<Imagen> getImagenesByProducto(int productoId) {
        return repositorioImagen.findByProductoId(productoId);
    }

    @Override
    public void deleteImagen(int id) {
        repositorioImagen.deleteById(id);
    }

    @Override
    public Optional<Imagen> getImagenById(Integer id) {
        return repositorioImagen.findById(id);
    }

    @Override
    public Optional<Imagen> updateImagen(Integer id, Imagen imagen) {
        Optional<Imagen> imagenExistente = repositorioImagen.findById(id);

        if (imagenExistente.isEmpty()) {
            return Optional.empty();
        }

        Imagen actualizada = imagenExistente.get();
        actualizada.setUrl(imagen.getUrl());
        actualizada.setEsPrincipal(imagen.isEsPrincipal());
        actualizada.setProducto(imagen.getProducto());

        return Optional.of(repositorioImagen.save(actualizada));
    }
}