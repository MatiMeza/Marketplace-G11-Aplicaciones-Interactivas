package com.uade.tpo.demo.controlador;

import com.uade.tpo.demo.entidades.Rol;
import com.uade.tpo.demo.entidades.Usuario;
import com.uade.tpo.demo.entidades.dto.RespuestaLogin;
import com.uade.tpo.demo.entidades.dto.SolicitudLogin;
import com.uade.tpo.demo.repositorios.RepositorioRol;
import com.uade.tpo.demo.repositorios.RepositorioUsuario;
import com.uade.tpo.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class ControladorDeAutenticacion {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager gestorAutenticacion;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioRol repositorioRol;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SolicitudLogin solicitud) {
        try {
            gestorAutenticacion.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            solicitud.getEmail(),
                            solicitud.getPassword()
                    )
            );
            String token = jwtUtil.generarToken(solicitud.getEmail());
            return ResponseEntity.ok(new RespuestaLogin(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email o contraseña incorrectos");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno: " + e.getMessage());
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody SolicitudLogin solicitud) {
        try {
            if (repositorioUsuario.findByEmail(solicitud.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("El email ya está registrado");
            }

            Rol rolUser = repositorioRol.findByNombre("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));

            Usuario nuevo = new Usuario();
            nuevo.setEmail(solicitud.getEmail());
            nuevo.setPassword(solicitud.getPassword());
            nuevo.setRoles(List.of(rolUser));

            repositorioUsuario.save(nuevo);

            String token = jwtUtil.generarToken(solicitud.getEmail());
            return ResponseEntity.ok(new RespuestaLogin(token));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar: " + e.getMessage());
        }
    }
}