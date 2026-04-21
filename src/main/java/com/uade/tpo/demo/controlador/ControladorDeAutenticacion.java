package com.uade.tpo.demo.controlador;

import com.uade.tpo.demo.entidades.dto.RespuestaLogin;
import com.uade.tpo.demo.entidades.dto.SolicitudLogin;
import com.uade.tpo.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class ControladorDeAutenticacion {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager gestorAutenticacion;

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
}