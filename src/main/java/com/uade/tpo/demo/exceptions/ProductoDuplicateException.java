package com.uade.tpo.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El producto ya existe.")
public class ProductoDuplicateException extends Exception {
    public ProductoDuplicateException() {
        super("El producto ya existe.");
    }
}