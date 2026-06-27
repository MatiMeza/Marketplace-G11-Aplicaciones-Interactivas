package com.uade.tpo.demo.entidades;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(name = "email_usuario")
    private String emailUsuario;

    @Column(name = "fecha_pedido")
    private LocalDate fechaPedido;

    @Column(name = "total")
    private double total;

    @Column(name = "estado")
    private String estado; // PENDIENTE, ENVIADO, ENTREGADO

    @Column(name = "direccion_envio")
    private String direccionEnvio;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles;

    public Pedido() {}
}