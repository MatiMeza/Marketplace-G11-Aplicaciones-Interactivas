package com.uade.tpo.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfiguracionSeguridad {

    @Autowired
    private ServicioDetallesUsuario servicioDetallesUsuario;

    @Bean
    public SecurityFilterChain cadenaDeFiltros(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .authenticationProvider(proveedorAutenticacion());

        return http.build();
    }

    @Bean
    public AuthenticationProvider proveedorAutenticacion() {
        DaoAuthenticationProvider proveedor = new DaoAuthenticationProvider();
        proveedor.setUserDetailsService(servicioDetallesUsuario);
        proveedor.setPasswordEncoder(codificadorPassword());
        return proveedor;
    }

    @Bean
    public PasswordEncoder codificadorPassword() {
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager gestorAutenticacion(AuthenticationConfiguration configuracion) throws Exception {
        return configuracion.getAuthenticationManager();
    }
}