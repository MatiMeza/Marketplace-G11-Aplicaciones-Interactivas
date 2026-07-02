package com.uade.tpo.demo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "descuentos")
public class Descuento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private double porcentaje;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @JsonIgnore
    @ManyToMany(mappedBy = "descuentos")
    private List<Producto> productos;
}