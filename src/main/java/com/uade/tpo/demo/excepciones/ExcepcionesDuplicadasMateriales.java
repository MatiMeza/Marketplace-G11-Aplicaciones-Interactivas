package com.uade.tpo.demo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El material ya existe.")
public class ExcepcionesDuplicadasMateriales extends Exception {
    public ExcepcionesDuplicadasMateriales() {
        super("El material ya existe.");
    }
}