package com.uade.tpo.demo.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El cupon que se intenta agregar ya existe")
public class ExcepcionesDuplicadasCupon extends Exception {
}