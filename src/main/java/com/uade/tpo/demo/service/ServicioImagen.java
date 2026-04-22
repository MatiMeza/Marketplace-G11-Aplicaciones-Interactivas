package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Imagen;
import java.util.List;
import java.util.Optional;

public interface ServicioImagen {
    Imagen saveImagen(Imagen imagen);
    List<Imagen> getImagenesByProducto(int productoId);
    Optional<Imagen> getImagenById(Integer id);
    Optional<Imagen> updateImagen(Integer id, Imagen imagen);
    void deleteImagen(int id);
}