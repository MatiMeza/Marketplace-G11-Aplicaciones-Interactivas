package com.uade.tpo.demo.controlador;

import com.uade.tpo.demo.entidades.DetallePedido;
import com.uade.tpo.demo.entidades.Pedido;
import com.uade.tpo.demo.entidades.Usuario;
import com.uade.tpo.demo.entidades.dto.SolicitudPedido;
import com.uade.tpo.demo.repositorios.RepositorioPedido;
import com.uade.tpo.demo.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class ControladorDePedidos {

    @Autowired
    private RepositorioPedido repositorioPedido;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    // GET /pedidos — todos los pedidos (solo ADMIN)
    @GetMapping
    public ResponseEntity<List<Pedido>> getTodosPedidos() {
        return ResponseEntity.ok(repositorioPedido.findAllByOrderByFechaPedidoDesc());
    }

    // GET /pedidos/mis-pedidos — pedidos del usuario logueado
    @GetMapping("/mis-pedidos")
    public ResponseEntity<List<Pedido>> getMisPedidos(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(repositorioPedido.findByEmailUsuarioOrderByFechaPedidoDesc(email));
    }

    // POST /pedidos — crear pedido
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody SolicitudPedido solicitud,
                                              Authentication authentication) {
        String email = authentication.getName();
        Usuario usuario = repositorioUsuario.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEmailUsuario(email);
        pedido.setFechaPedido(LocalDate.now());
        pedido.setTotal(solicitud.getTotal());
        pedido.setEstado("PENDIENTE");
        pedido.setDireccionEnvio(solicitud.getDireccionEnvio() != null ? solicitud.getDireccionEnvio() : "");

        List<DetallePedido> detalles = solicitud.getProductos().stream().map(item -> {
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setIdProducto(item.getIdProducto());
            detalle.setNombreProducto(item.getNombreProducto());
            detalle.setPrecioUnitario(item.getPrecioUnitario());
            detalle.setCantidad(item.getCantidad());
            return detalle;
        }).collect(Collectors.toList());

        pedido.setDetalles(detalles);

        Pedido guardado = repositorioPedido.save(pedido);
        return ResponseEntity.ok(guardado);
    }

    // PUT /pedidos/{id}/estado — cambiar estado (solo ADMIN)
    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstado(@PathVariable Long id,
                                                   @RequestBody java.util.Map<String, String> body) {
        return repositorioPedido.findById(id).map(pedido -> {
            pedido.setEstado(body.get("estado"));
            return ResponseEntity.ok(repositorioPedido.save(pedido));
        }).orElse(ResponseEntity.notFound().build());
    }
}