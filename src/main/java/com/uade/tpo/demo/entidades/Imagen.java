package com.uade.tpo.demo.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "imagenes")
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String url;

    @Column(name = "es_portada", nullable = false)
    private boolean esPrincipal;

    // WRITE_ONLY: se acepta en el POST/PUT (para setear la FK con { "id": ... })
    // pero no se incluye al serializar la respuesta, evitando el loop infinito
    // Producto -> imagenes -> producto -> imagenes -> ...
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
}