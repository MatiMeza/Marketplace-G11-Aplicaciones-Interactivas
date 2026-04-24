package com.uade.tpo.demo.controlador;

import com.uade.tpo.demo.entidades.Carrito;
import com.uade.tpo.demo.entidades.dto.SolicitudAgregarCarrito;
import com.uade.tpo.demo.service.ServicioCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
public class ControladorDeCarrito {

    @Autowired
    private ServicioCarrito servicioCarrito;

    @GetMapping
    public ResponseEntity<Carrito> obtenerCarrito(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(servicioCarrito.obtenerCarrito(email));
    }

    @PostMapping("/agregar")
    public ResponseEntity<Carrito> agregarProducto(
            Authentication authentication,
            @RequestBody SolicitudAgregarCarrito solicitud
    ) {
        String email = authentication.getName();

        Carrito carrito = servicioCarrito.agregarProducto(
                email,
                solicitud.getProductoId(),
                solicitud.getCantidad()
        );

        return ResponseEntity.ok(carrito);
    }

    @DeleteMapping("/vaciar")
    public ResponseEntity<Carrito> vaciarCarrito(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(servicioCarrito.vaciarCarrito(email));
    }
}