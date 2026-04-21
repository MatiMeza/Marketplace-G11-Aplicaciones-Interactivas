package com.uade.tpo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.demo.entidades.Imagen;
import com.uade.tpo.demo.respositorios.RepositorioImagen;
import java.util.List;

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
}