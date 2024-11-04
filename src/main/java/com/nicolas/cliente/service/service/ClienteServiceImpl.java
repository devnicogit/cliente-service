package com.nicolas.cliente.service.service;

import com.nicolas.cliente.service.dto.ClienteDTO;
import com.nicolas.cliente.service.exception.ClienteNotFoundException;
import com.nicolas.cliente.service.mapper.ClienteMapper;
import com.nicolas.cliente.service.messaging.ClienteEventPublisher;
import com.nicolas.cliente.service.model.Cliente;
import com.nicolas.cliente.service.repository.ClienteRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final ClienteEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper, ClienteEventPublisher eventPublisher, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.eventPublisher = eventPublisher;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retorna una lista de todos los clientes, convertidos en DTOs.
     * @return Lista de ClienteDTO
     */
    @Override
    public List<ClienteDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Retorna un cliente específico por su ID.
     * Si el cliente no se encuentra, lanza una excepción personalizada.
     * @param id ID del cliente
     * @return ClienteDTO encontrado
     */
    @Override
    public ClienteDTO getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + id + " no encontrado"));
        return clienteMapper.toDto(cliente);
    }

    /**
     * Crea un nuevo cliente. Se cifra la contraseña antes de guardar el cliente.
     * @param clienteDTO DTO con los datos del cliente
     * @param password Contraseña del cliente
     * @return ClienteDTO del cliente creado
     */
    @Override
    public ClienteDTO createCliente(ClienteDTO clienteDTO, String password) {

        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        cliente.setContraseña(passwordEncoder.encode(password));
        Cliente savedCliente = clienteRepository.save(cliente);
        eventPublisher.publish(savedCliente);
        return clienteMapper.toDto(savedCliente);
    }

    /**
     * Actualiza los datos de un cliente existente.
     * @param id ID del cliente a actualizar
     * @param clienteDTO DTO con los datos actualizados del cliente
     * @return ClienteDTO con los datos actualizados
     */
    @Override
    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Cliente existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + id + " no encontrado"));

        existingCliente.setNombre(clienteDTO.getNombre());
        existingCliente.setGenero(clienteDTO.getGenero());
        existingCliente.setEdad(clienteDTO.getEdad());
        existingCliente.setIdentificacion(clienteDTO.getIdentificacion());
        existingCliente.setDireccion(clienteDTO.getDireccion());
        existingCliente.setTelefono(clienteDTO.getTelefono());
        existingCliente.setEstado(clienteDTO.isEstado());

        Cliente updatedCliente = clienteRepository.save(existingCliente);

        return clienteMapper.toDto(updatedCliente);
    }

    /**
     * Elimina un cliente por su ID.
     * Si no se encuentra, lanza una excepción personalizada.
     * @param id ID del cliente
     */
    @Override
    public void deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + id + " no encontrado"));
        clienteRepository.delete(cliente);
    }
}