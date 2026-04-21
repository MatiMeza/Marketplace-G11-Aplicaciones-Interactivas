package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Imagen;
import java.util.List;

public interface ServicioImagen {
    Imagen saveImagen(Imagen imagen);
    List<Imagen> getImagenesByProducto(int productoId);
    void deleteImagen(int id);
}