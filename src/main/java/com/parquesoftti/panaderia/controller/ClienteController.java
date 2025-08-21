package com.parquesoftti.panaderia.controller;

import com.parquesoftti.panaderia.dto.ClienteDTORespuesta;
import com.parquesoftti.panaderia.dto.ClienteDTO;
import com.parquesoftti.panaderia.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Obtener todos los clientes como DTO
    @GetMapping
    public ResponseEntity<List<ClienteDTORespuesta>> getAllClientes() {
        List<ClienteDTORespuesta> clientes = clienteService.getAllClients();
        return ResponseEntity.ok(clientes);
    }

    // Obtener cliente por nombre como DTO
    @GetMapping("/name")
    public ResponseEntity<ClienteDTORespuesta> getClienteByName(@RequestParam String name) {
        ClienteDTORespuesta respuesta = clienteService.getClientByName(name);
        return ResponseEntity.ok(respuesta);
    }

    // Obtener cliente por ID como DTO
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTORespuesta> getClienteById(@PathVariable Long id) {
        ClienteDTORespuesta respuesta = clienteService.findById(id);
        return ResponseEntity.ok(respuesta);
    }

    // Crear un nuevo cliente (devuelve DTO)
    @PostMapping
    public ResponseEntity<ClienteDTORespuesta> createCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTORespuesta nuevoCliente = clienteService.save(convertirADTO(clienteDTO));
        return ResponseEntity.ok(nuevoCliente);
    }

    // Actualizar cliente por ID (devuelve DTO actualizado)
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTORespuesta> updateCliente(
            @PathVariable Long id,
            @RequestBody ClienteDTO clienteDTO) {
        ClienteDTORespuesta clienteActualizado = clienteService.update(id, convertirAEntidad(id, clienteDTO));
        return ResponseEntity.ok(clienteActualizado);
    }

    // Eliminar cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // metodo auxiliar para convertir ClienteDTO a Cliente (entidad)
    private com.parquesoftti.panaderia.model.Cliente convertirAEntidad(Long id, ClienteDTO dto) {
        com.parquesoftti.panaderia.model.Cliente cliente = new com.parquesoftti.panaderia.model.Cliente();
        cliente.setId(id);
        cliente.setNombre(dto.getNombre());
        cliente.setTelefono(dto.getTelefono());
        return cliente;
    }

    // Metodo auxiliar para crear Cliente desde ClienteDTO sin ID (para crear)
    private com.parquesoftti.panaderia.model.Cliente convertirADTO(ClienteDTO dto) {
        com.parquesoftti.panaderia.model.Cliente cliente = new com.parquesoftti.panaderia.model.Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setTelefono(dto.getTelefono());
        return cliente;
    }
}