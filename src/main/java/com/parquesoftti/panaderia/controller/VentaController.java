package com.parquesoftti.panaderia.controller;

import com.parquesoftti.panaderia.dto.VentaDTO;
import com.parquesoftti.panaderia.dto.VentaDTORespuesta;
import com.parquesoftti.panaderia.service.VentaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // Obtener todas las ventas
    @GetMapping
    public ResponseEntity<List<VentaDTORespuesta>> getAllVentas() {
        List<VentaDTORespuesta> ventas = ventaService.getAllVentas();
        return ResponseEntity.ok(ventas);
    }

    // Obtener una venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTORespuesta> getVentaById(@PathVariable Long id) {
        VentaDTORespuesta respuesta = ventaService.getVentaById(id);
        return ResponseEntity.ok(respuesta);
    }

    // Obtener ventas por cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<VentaDTORespuesta>> getVentasByCliente(@PathVariable Long clienteId) {
        List<VentaDTORespuesta> ventas = ventaService.getVentasByCliente(clienteId);
        return ResponseEntity.ok(ventas);
    }

    // Obtener ventas por producto
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<VentaDTORespuesta>> getVentasByProducto(@PathVariable Long productoId) {
        List<VentaDTORespuesta> ventas = ventaService.getVentasByProducto(productoId);
        return ResponseEntity.ok(ventas);
    }

    // Obtener ventas por cliente y producto como DTO
    @GetMapping("/cliente/{clienteId}/producto/{productoId}")
    public ResponseEntity<List<VentaDTORespuesta>> getVentasPorClienteYProducto(
            @PathVariable Long clienteId,
            @PathVariable Long productoId) {
        List<VentaDTORespuesta> ventas = ventaService.getVentasPorClienteYProducto(clienteId, productoId);
        return ResponseEntity.ok(ventas);
    }

    // Obtener ventas por fecha específica como DTO
    @GetMapping("/fecha")
    public ResponseEntity<List<VentaDTORespuesta>> getVentasPorFecha(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha) {
        List<VentaDTORespuesta> ventas = ventaService.getVentasPorFecha(fecha);
        return ResponseEntity.ok(ventas);
    }

    // Registrar venta
    @PostMapping("/registrar")
    public ResponseEntity<VentaDTORespuesta> registrarVenta(@RequestBody VentaDTO ventaDTO) {

        if (ventaDTO.getClienteId() == null || ventaDTO.getProductoId() == null || ventaDTO.getCantidad() == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        VentaDTORespuesta respuesta = ventaService.registrarVenta(ventaDTO);
        return ResponseEntity.ok(respuesta);
    }

    // Eliminar venta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}