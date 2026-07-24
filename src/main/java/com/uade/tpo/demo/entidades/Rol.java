package com.uade.tpo.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")

public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;
}