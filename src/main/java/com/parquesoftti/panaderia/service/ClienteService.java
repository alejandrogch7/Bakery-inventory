package com.parquesoftti.panaderia.service;

import com.parquesoftti.panaderia.dto.ClienteDTORespuesta;
import com.parquesoftti.panaderia.exception.ClienteNotFoundException;
import com.parquesoftti.panaderia.model.Cliente;
import com.parquesoftti.panaderia.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ClienteDTORespuesta> getAllClients(){
        return clienteRepository.findAll().stream()
                .map(cliente -> new ClienteDTORespuesta(
                        cliente.getId(),
                        cliente.getNombre(),
                        cliente.getTelefono()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteDTORespuesta getClientByName(String name) {
        Cliente cliente = clienteRepository.findByNombre(name)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con nombre: " + name));

        return new ClienteDTORespuesta(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getTelefono()
        );
    }

    @Transactional
    public ClienteDTORespuesta save(Cliente cliente){
        Cliente guardado = clienteRepository.save(cliente);
        return new ClienteDTORespuesta(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getTelefono()
        );
    }

    @Transactional
    public void deleteById(Long id){
        clienteRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ClienteDTORespuesta findById(Long id){
        return clienteRepository.findById(id)
                .map(cliente -> new ClienteDTORespuesta(
                        cliente.getId(),
                        cliente.getNombre(),
                        cliente.getTelefono()
                ))
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con ID: " + id));
    }

    @Transactional
    public ClienteDTORespuesta update(Long id, Cliente clienteActualizado) {
        Cliente tmp = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con ID: " + id));

        tmp.setNombre(clienteActualizado.getNombre());
        tmp.setTelefono(clienteActualizado.getTelefono());

        Cliente guardado = clienteRepository.save(tmp);

        return new ClienteDTORespuesta(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getTelefono()
        );
    }
}