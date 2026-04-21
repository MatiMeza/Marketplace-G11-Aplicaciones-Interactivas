package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entity.Imagen;
import java.util.List;

public interface ImagenService {
    Imagen saveImagen(Imagen imagen);
    List<Imagen> getImagenesByProducto(int productoId);
    void deleteImagen(int id);
}