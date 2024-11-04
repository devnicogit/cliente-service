package com.nicolas.cliente.service.dto;

import lombok.Data;

@Data
public class ClienteDTO {

    private Long clienteId;
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    private boolean estado;

    // Evitamos incluir información sensible como la contraseña
}
