package com.uade.tpo.demo.entidades.dto;

import lombok.Data;

@Data
public class SolicitudLogin {
    private String email;
    private String password;
}