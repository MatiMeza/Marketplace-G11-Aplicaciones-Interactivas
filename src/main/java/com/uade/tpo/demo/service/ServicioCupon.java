package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Cupon;
import com.uade.tpo.demo.entidades.dto.SolicitudDeCupon;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasCupon;
import java.util.List;
import java.util.Optional;

public interface ServicioCupon {
    List<Cupon> getCupones();
    Optional<Cupon> getCuponById(Long id);
    Optional<Cupon> getCuponByCodigo(String codigo);
    Cupon createCupon(SolicitudDeCupon solicitud) throws ExcepcionesDuplicadasCupon;
    boolean deleteCupon(Long id);
}