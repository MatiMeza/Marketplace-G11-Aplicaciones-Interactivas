package com.uade.tpo.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El material ya existe.")
public class MaterialDuplicateException extends Exception {
    public MaterialDuplicateException() {
        super("El material ya existe.");
    }
}