package com.uade.tpo.demo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El producto ya existe.")
public class ExcepcionesDuplicadasProducto extends Exception {
    public ExcepcionesDuplicadasProducto() {
        super("El producto ya existe.");
    }
}