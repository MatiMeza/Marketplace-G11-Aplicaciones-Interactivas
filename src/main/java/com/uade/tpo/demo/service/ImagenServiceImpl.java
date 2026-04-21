package com.uade.tpo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uade.tpo.demo.entity.Imagen;
import com.uade.tpo.demo.repository.ImagenRepository;
import java.util.List;

@Service
public class ImagenServiceImpl implements ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    @Override
    public Imagen saveImagen(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    @Override
    public List<Imagen> getImagenesByProducto(int productoId) {
        return imagenRepository.findByProductoId(productoId);
    }

    @Override
    public void deleteImagen(int id) {
        imagenRepository.deleteById(id);
    }
}