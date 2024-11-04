package com.nicolas.cliente.service.service;

import com.nicolas.cliente.service.dto.ClienteDTO;
import com.nicolas.cliente.service.model.Cliente;

import java.util.List;

public interface ClienteService {


    List<ClienteDTO> getAllClientes();
    ClienteDTO getClienteById(Long id);
    ClienteDTO createCliente(ClienteDTO clienteDTO, String password);
    ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO);
    void deleteCliente(Long id);
}
