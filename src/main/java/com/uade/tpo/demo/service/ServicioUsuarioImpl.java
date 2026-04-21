package com.uade.tpo.demo.service;

import com.uade.tpo.demo.entidades.Usuario;
import com.uade.tpo.demo.respositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario {
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Override
    public Usuario registrar(Usuario usuario) {
        return repositorioUsuario.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repositorioUsuario.findByEmail(email);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return repositorioUsuario.findById(id);
    }
}
