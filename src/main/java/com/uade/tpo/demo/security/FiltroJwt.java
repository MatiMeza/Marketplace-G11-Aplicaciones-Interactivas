package com.uade.tpo.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroJwt extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ServicioDetallesUsuario servicioDetallesUsuario;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String ruta = request.getServletPath();

        if (ruta.startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String encabezadoAutorizacion = request.getHeader("Authorization");
        String token = null;
        String email = null;

        if (encabezadoAutorizacion != null && encabezadoAutorizacion.startsWith("Bearer ")) {
            token = encabezadoAutorizacion.substring(7);
            email = jwtUtil.extraerEmail(token);
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails detallesUsuario = servicioDetallesUsuario.loadUserByUsername(email);

            if (jwtUtil.validarToken(token)) {
                UsernamePasswordAuthenticationToken autenticacion =
                        new UsernamePasswordAuthenticationToken(
                                detallesUsuario,
                                null,
                                detallesUsuario.getAuthorities()
                        );

                autenticacion.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(autenticacion);
            }
        }

        filterChain.doFilter(request, response);
    }
}