package com.uade.tpo.demo.controlador;

import com.uade.tpo.demo.entidades.DetallePedido;
import com.uade.tpo.demo.entidades.Pedido;
import com.uade.tpo.demo.entidades.Producto;
import com.uade.tpo.demo.entidades.Usuario;
import com.uade.tpo.demo.entidades.Cupon;
import com.uade.tpo.demo.entidades.dto.SolicitudPedido;
import com.uade.tpo.demo.repositorios.RepositorioPedido;
import com.uade.tpo.demo.repositorios.RepositorioUsuario;
import com.uade.tpo.demo.repositorios.RepositorioProducto;
import com.uade.tpo.demo.repositorios.RepositorioCupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class ControladorDePedidos {

    @Autowired
    private RepositorioPedido repositorioPedido;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioProducto repositorioProducto;

    @Autowired
    private RepositorioCupon repositorioCupon;

    @GetMapping
    public ResponseEntity<List<Pedido>> getTodosPedidos() {
        return ResponseEntity.ok(repositorioPedido.findAllByOrderByFechaPedidoDesc());
    }

    @GetMapping("/mis-pedidos")
    public ResponseEntity<List<Pedido>> getMisPedidos(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(repositorioPedido.findByEmailUsuarioOrderByFechaPedidoDesc(email));
    }

    @PostMapping
    @Transactional // Fundamental: revierte cambios en la BD (ej. stock) si ocurre una excepción
    public ResponseEntity<?> crearPedido(@RequestBody SolicitudPedido solicitud, 
                                         Authentication authentication) {
        
        // 1. Validación de usuario. 
        // Nota objetiva: Tu entidad Pedido.java tiene @JoinColumn(nullable = false) para id_usuario.
        // Por ende, la base de datos rechazará compras de usuarios no autenticados (invitados).
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Debe estar autenticado para realizar una compra.");
        }

        String email = authentication.getName();
        Usuario usuario = repositorioUsuario.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (solicitud.getProductos() == null || solicitud.getProductos().isEmpty()) {
            return ResponseEntity.badRequest().body("El pedido debe contener al menos un producto.");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEmailUsuario(email);
        pedido.setFechaPedido(LocalDate.now());
        pedido.setEstado("PENDIENTE");
        pedido.setDireccionEnvio(solicitud.getDireccionEnvio() != null ? solicitud.getDireccionEnvio() : "");

        double subtotal = 0.0;
        List<DetallePedido> detalles = new ArrayList<>();

        // 2. Procesamiento, validación de stock y cálculo de precios usando datos del servidor
        for (SolicitudPedido.ItemPedido item : solicitud.getProductos()) {
            Producto producto = repositorioProducto.findById(item.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getIdProducto()));

            if (producto.getStock() < item.getCantidad()) {
                return ResponseEntity.badRequest().body("Stock insuficiente para el producto: " + producto.getNombre());
            }

            // Descuento de stock atómico
            producto.setStock(producto.getStock() - item.getCantidad());
            repositorioProducto.save(producto);

            subtotal += (producto.getPrecio() * item.getCantidad());

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setIdProducto(producto.getId());
            detalle.setNombreProducto(producto.getNombre());
            detalle.setPrecioUnitario(producto.getPrecio()); // El precio se extrae de la BD, no del request
            detalle.setCantidad(item.getCantidad());
            detalles.add(detalle);
        }

        // 3. Aplicación de la lógica de descuentos
        double totalFinal = subtotal;
        if (solicitud.getCuponAplicado() != null && !solicitud.getCuponAplicado().trim().isEmpty()) {
            Optional<Cupon> cuponOpt = repositorioCupon.findByCodigo(solicitud.getCuponAplicado().toUpperCase());
            if (cuponOpt.isPresent()) {
                double porcentajeDescuento = cuponOpt.get().getDescuento();
                double montoDescuento = subtotal * (porcentajeDescuento / 100);
                totalFinal = subtotal - Math.round(montoDescuento);
            } else {
                return ResponseEntity.badRequest().body("El cupón proporcionado no existe o no es válido.");
            }
        }

        pedido.setTotal(totalFinal);
        pedido.setDetalles(detalles);

        Pedido guardado = repositorioPedido.save(pedido);
        return ResponseEntity.ok(guardado);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable Long id,
                                                   @RequestBody java.util.Map<String, String> body) {
        return repositorioPedido.findById(id).map(pedido -> {
            pedido.setEstado(body.get("estado"));
            return ResponseEntity.ok(repositorioPedido.save(pedido));
        }).orElse(ResponseEntity.notFound().build());
    }
}