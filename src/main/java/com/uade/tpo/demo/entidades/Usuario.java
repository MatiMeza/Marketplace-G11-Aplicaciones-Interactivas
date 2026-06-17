package com.uade.tpo.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private List<Rol> roles;

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Carrito carrito;

    public Usuario() {}
}