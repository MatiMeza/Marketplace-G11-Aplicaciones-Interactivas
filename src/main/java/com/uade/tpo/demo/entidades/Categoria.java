package com.uade.tpo.demo.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Categoria {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Column
    private String description;

    public Categoria() {
    }

    public Categoria(String description) {
        this.description = description;
    }
}
