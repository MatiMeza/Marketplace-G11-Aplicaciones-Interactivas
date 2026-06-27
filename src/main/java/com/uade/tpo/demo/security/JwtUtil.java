package com.uade.tpo.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final String CLAVE_SECRETA = "clave_secreta";

    @Autowired
    private ServicioDetallesUsuario servicioDetallesUsuario;

    // Genera token incluyendo los roles del usuario
    public String generarToken(String email) {
        UserDetails userDetails = servicioDetallesUsuario.loadUserByUsername(email);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, CLAVE_SECRETA)
                .compact();
    }

    public String extraerEmail(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(CLAVE_SECRETA)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(CLAVE_SECRETA)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}