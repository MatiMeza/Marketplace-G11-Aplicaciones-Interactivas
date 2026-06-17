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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(proveedorAutenticacion())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/productos/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/productos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/productos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/productos/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/materiales/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/materiales/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/materiales/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/materiales/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/usuarios/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")

                        .requestMatchers("/carrito/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/categorias/**").authenticated()
                        .requestMatchers("/imagenes/**").authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(filtroJwt, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
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