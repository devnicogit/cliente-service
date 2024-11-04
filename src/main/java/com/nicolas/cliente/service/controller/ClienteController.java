package com.nicolas.cliente.service.controller;

import com.nicolas.cliente.service.dto.ClienteDTO;
import com.nicolas.cliente.service.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Retorna una lista de todos los clientes.
     */
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<ClienteDTO> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes); // Devuelve un 200 OK con la lista de clientes
    }

    /**
     * Retorna un cliente específico por su ID.
     * @param id ID del cliente
     * @return ClienteDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente); // Devuelve un 200 OK con el cliente encontrado
    }

    /**
     * Crea un nuevo cliente.
     * Recibe un ClienteDTO y la contraseña por separado.
     * @param clienteDTO DTO con los datos del cliente
     * @param password Contraseña del cliente
     * @return ClienteDTO creado
     */
    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO, @RequestParam String password) {
        ClienteDTO createdCliente = clienteService.createCliente(clienteDTO, password);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente); // Devuelve un 201 Created con el cliente creado
    }

    /**
     * Actualiza un cliente existente.
     * @param id ID del cliente a actualizar
     * @param clienteDTO DTO con los datos actualizados del cliente
     * @return ClienteDTO actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO updatedCliente = clienteService.updateCliente(id, clienteDTO);
        return ResponseEntity.ok(updatedCliente); // Devuelve un 200 OK con el cliente actualizado
    }

    /**
     * Elimina un cliente por su ID.
     * @param id ID del cliente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build(); // Devuelve un 204 No Content después de eliminar
    }
}
