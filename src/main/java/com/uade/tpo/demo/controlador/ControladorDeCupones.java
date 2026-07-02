package com.uade.tpo.demo.controlador;

import com.uade.tpo.demo.entidades.Cupon;
import com.uade.tpo.demo.entidades.dto.SolicitudDeCupon;
import com.uade.tpo.demo.excepciones.ExcepcionesDuplicadasCupon;
import com.uade.tpo.demo.service.ServicioCupon;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cupones")
public class ControladorDeCupones {

    @Autowired
    private ServicioCupon servicioCupon;

    @GetMapping
    public ResponseEntity<List<Cupon>> getCupones() {
        return ResponseEntity.ok(servicioCupon.getCupones());
    }

    @GetMapping("/validar/{codigo}")
    public ResponseEntity<Cupon> validarCupon(@PathVariable String codigo) {
        Optional<Cupon> result = servicioCupon.getCuponByCodigo(codigo);
        if (result.isPresent()) return ResponseEntity.ok(result.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Object> createCupon(@RequestBody SolicitudDeCupon solicitud) {
        try {
            Cupon result = servicioCupon.createCupon(solicitud);
            return ResponseEntity.created(URI.create("/cupones/" + result.getId())).body(result);
        } catch (ExcepcionesDuplicadasCupon e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCupon(@PathVariable Long id) {
        boolean eliminado = servicioCupon.deleteCupon(id);
        if (eliminado) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}