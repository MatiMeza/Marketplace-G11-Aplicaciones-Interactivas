package com.uade.tpo.demo.controlador;

import com.uade.tpo.demo.entidades.Usuario;
import com.uade.tpo.demo.service.ServicioUsuario;
import com.uade.tpo.demo.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class ControladorDeUsuarios {

    @Autowired
    private ServicioUsuario servicioUsuario;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(repositorioUsuario.findAll());
    }

    @GetMapping("/perfil")
    public ResponseEntity<Object> getPerfil(Authentication authentication) {
        String email = authentication.getName();
        return repositorioUsuario.findByEmail(email)
                .map(u -> ResponseEntity.ok((Object) u))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable Long id) {
        return repositorioUsuario.findById(id)
                .map(u -> ResponseEntity.ok((Object) u))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/perfil")
    public ResponseEntity<Object> actualizarPerfil(Authentication authentication,
                                                   @RequestBody Map<String, String> body) {
        String email = authentication.getName();
        return repositorioUsuario.findByEmail(email).map(usuario -> {
            if (body.containsKey("nombre")) usuario.setNombre(body.get("nombre"));
            if (body.containsKey("telefono")) usuario.setTelefono(body.get("telefono"));
            if (body.containsKey("direccion")) usuario.setDireccion(body.get("direccion"));
            repositorioUsuario.save(usuario);
            return ResponseEntity.ok((Object) usuario);
        }).orElse(ResponseEntity.notFound().build());
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