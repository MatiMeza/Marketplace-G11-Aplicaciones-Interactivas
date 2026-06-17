package com.uade.tpo.demo.controlador;

import com.uade.tpo.demo.entidades.Usuario;
import com.uade.tpo.demo.service.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class ControladorDeUsuarios {

    @Autowired
    private ServicioUsuario servicioUsuario;

    @Autowired
    private com.uade.tpo.demo.repositorios.RepositorioUsuario repositorioUsuario;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(repositorioUsuario.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable Long id) {
        return repositorioUsuario.findById(id)
                .map(u -> ResponseEntity.ok((Object) u))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        if (repositorioUsuario.existsById(id)) {
            repositorioUsuario.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}