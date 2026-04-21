package com.uade.tpo.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.uade.tpo.demo.entidades.Usuario;
import com.uade.tpo.demo.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class ControladorDeAutenticacion {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        return jwtUtil.generarToken(usuario.getEmail());
    }
}