package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Carrito;
import com.uade.tpo.demo.entidades.DetalleCarrito;
import com.uade.tpo.demo.entidades.Producto;
import com.uade.tpo.demo.entidades.Usuario;
import com.uade.tpo.demo.repositorios.RepositorioCarrito;
import com.uade.tpo.demo.repositorios.RepositorioProducto;
import com.uade.tpo.demo.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class ServicioCarritoImpl implements ServicioCarrito {

    @Autowired
    private RepositorioCarrito repositorioCarrito;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioProducto repositorioProducto;

    @Override
    public Carrito obtenerCarrito(String email) {
        return repositorioCarrito.findByUsuarioEmail(email)
                .orElseGet(() -> crearCarrito(email));
    }

    @Override
    public Carrito agregarProducto(String email, Long productoId, int cantidad) {
        Carrito carrito = obtenerCarrito(email);

        Producto producto = repositorioProducto.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        carrito.agregarProducto(producto, cantidad);

        return repositorioCarrito.save(carrito);
    }

    @Override
    public Carrito vaciarCarrito(String email) {
        Carrito carrito = obtenerCarrito(email);
        carrito.vaciarCarrito();
        return repositorioCarrito.save(carrito);
    }

    // NUEVO: descuenta stock y vacía carrito en una sola transacción
    @Override
    @Transactional
    public void confirmarCompra(String email) {
        Carrito carrito = obtenerCarrito(email);

        for (DetalleCarrito detalle : carrito.getDetalles()) {
            Producto producto = detalle.getProducto();
            int nuevoStock = Math.max(0, producto.getStock() - detalle.getCantidad());
            producto.setStock(nuevoStock);
            repositorioProducto.save(producto);
        }

        carrito.vaciarCarrito();
        repositorioCarrito.save(carrito);
    }

    private Carrito crearCarrito(String email) {
        Usuario usuario = repositorioUsuario.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = new Carrito();
        carrito.setUsuario(usuario);
        carrito.setFechaCreacion(LocalDate.now());

        return repositorioCarrito.save(carrito);
    }
}