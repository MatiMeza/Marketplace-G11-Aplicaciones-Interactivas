package com.uade.tpo.demo.controlador;

import com.uade.tpo.demo.entidades.dto.RespuestaLogin;
import com.uade.tpo.demo.entidades.dto.SolicitudLogin;
import com.uade.tpo.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
    public RespuestaLogin login(@RequestBody SolicitudLogin solicitud) {

        gestorAutenticacion.authenticate(
                new UsernamePasswordAuthenticationToken(
                        solicitud.getEmail(),
                        solicitud.getPassword()
                )
        );

        String token = jwtUtil.generarToken(solicitud.getEmail());

        return new RespuestaLogin(token);
    }
}