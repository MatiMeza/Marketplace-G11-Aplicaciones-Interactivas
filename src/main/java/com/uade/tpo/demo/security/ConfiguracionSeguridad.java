package com.uade.tpo.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class ConfiguracionSeguridad {

    @Autowired
    private ServicioDetallesUsuario servicioDetallesUsuario;

    @Autowired
    private FiltroJwt filtroJwt;

    @Bean
    public SecurityFilterChain cadenaDeFiltros(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sesion -> sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(proveedorAutenticacion())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()

                        .requestMatchers("/productos/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/materiales/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/materiales/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/materiales/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/materiales/**").hasRole("ADMIN")

                        .requestMatchers("/categories/**").authenticated()
                        .requestMatchers("/imagenes/**").authenticated()

                        .anyRequest().authenticated()
                )
                .addFilterBefore(filtroJwt, UsernamePasswordAuthenticationFilter.class);

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