package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Usuario;
import java.util.Optional;

public interface ServicioUsuario {
    Usuario registrar(Usuario usuario);
    Optional<Usuario> buscarPorEmail(String email);
    Optional<Usuario> buscarPorId(Long id);
}