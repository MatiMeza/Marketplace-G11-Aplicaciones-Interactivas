package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Cupon;
import com.uade.tpo.demo.entidades.dto.SolicitudDeCupon;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasCupon;
import com.uade.tpo.demo.repositorios.RepositorioCupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioCuponImpl implements ServicioCupon {

    @Autowired
    private RepositorioCupon repositorioCupon;

    @Override
    public List<Cupon> getCupones() {
        return repositorioCupon.findAll();
    }

    @Override
    public Optional<Cupon> getCuponById(Long id) {
        return repositorioCupon.findById(id);
    }

    @Override
    public Optional<Cupon> getCuponByCodigo(String codigo) {
        return repositorioCupon.findByCodigo(codigo.toUpperCase());
    }

    @Override
    public Cupon createCupon(SolicitudDeCupon solicitud) throws ExcepcionesDuplicadasCupon {
        String codigo = solicitud.getCodigo().toUpperCase();
        if (repositorioCupon.findByCodigo(codigo).isPresent()) {
            throw new ExcepcionesDuplicadasCupon();
        }
        Cupon nuevo = new Cupon();
        nuevo.setCodigo(codigo);
        nuevo.setDescuento(solicitud.getDescuento());
        return repositorioCupon.save(nuevo);
    }

    @Override
    public boolean deleteCupon(Long id) {
        if (!repositorioCupon.existsById(id)) return false;
        repositorioCupon.deleteById(id);
        return true;
    }
}