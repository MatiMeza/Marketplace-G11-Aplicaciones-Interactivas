package com.uade.tpo.demo.controlador;

import com.uade.tpo.demo.entidades.DetalleCarrito;
import com.uade.tpo.demo.entidades.dto.ItemCarrito;
import com.uade.tpo.demo.entidades.dto.RespuestaCarrito;
import java.util.List;
import java.util.stream.Collectors;
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
    public ResponseEntity<RespuestaCarrito> obtenerCarrito(Authentication authentication) {
        String email = authentication.getName();

        Carrito carrito = servicioCarrito.obtenerCarrito(email);

        List<ItemCarrito> productos = carrito.getDetalles()
                .stream()
                .map(detalle -> new ItemCarrito(
                        detalle.getProducto().getId(),
                        detalle.getProducto().getNombre(),
                        detalle.getProducto().getPrecio(),
                        detalle.getCantidad(),
                        detalle.calcularSubtotal()
                ))
                .collect(Collectors.toList());

        RespuestaCarrito respuesta = new RespuestaCarrito(
                carrito.getIdCarrito(),
                email,
                productos,
                carrito.calcularTotal()
        );

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarProducto(
            Authentication authentication,
            @RequestBody SolicitudAgregarCarrito solicitud
    ) {
        String email = authentication.getName();

        servicioCarrito.agregarProducto(
                email,
                solicitud.getProductoId(),
                solicitud.getCantidad()
        );

        return ResponseEntity.ok("Producto agregado al carrito correctamente");
    }

    @DeleteMapping("/vaciar")
    public ResponseEntity<Carrito> vaciarCarrito(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(servicioCarrito.vaciarCarrito(email));
    }
}