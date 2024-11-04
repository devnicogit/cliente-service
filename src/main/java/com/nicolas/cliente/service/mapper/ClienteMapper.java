package com.nicolas.cliente.service.mapper;

import com.nicolas.cliente.service.dto.ClienteDTO;
import com.nicolas.cliente.service.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    /**
     * Convierte de entidad Cliente a ClienteDTO.
     */
    public ClienteDTO toDto(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setClienteId(cliente.getClienteId());
        dto.setNombre(cliente.getNombre());
        dto.setGenero(cliente.getGenero());
        dto.setEdad(cliente.getEdad());
        dto.setIdentificacion(cliente.getIdentificacion());
        dto.setDireccion(cliente.getDireccion());
        dto.setTelefono(cliente.getTelefono());
        dto.setEstado(cliente.isEstado());
        return dto;
    }

    /**
     * Convierte de ClienteDTO a entidad Cliente.
     */
    public Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setClienteId(dto.getClienteId());
        cliente.setNombre(dto.getNombre());
        cliente.setGenero(dto.getGenero());
        cliente.setEdad(dto.getEdad());
        cliente.setIdentificacion(dto.getIdentificacion());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        cliente.setEstado(dto.isEstado());
        return cliente;
    }
}