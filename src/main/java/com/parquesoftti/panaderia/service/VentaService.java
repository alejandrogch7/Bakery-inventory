package com.parquesoftti.panaderia.service;

import com.parquesoftti.panaderia.dto.VentaDTO;
import com.parquesoftti.panaderia.dto.VentaDTORespuesta;
import com.parquesoftti.panaderia.model.Producto;
import com.parquesoftti.panaderia.model.Cliente;
import com.parquesoftti.panaderia.model.Venta;
import com.parquesoftti.panaderia.repository.ProductoRepository;
import com.parquesoftti.panaderia.repository.ClienteRepository;
import com.parquesoftti.panaderia.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // Devuelve todas las ventas como DTOs
    public List<VentaDTORespuesta> getAllVentas() {
        return ventaRepository.findAll().stream()
                .map(this::convertirADTORespuesta)
                .collect(Collectors.toList());
    }

    // Devuelve una venta por ID como DTO
    public VentaDTORespuesta getVentaById(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return convertirADTORespuesta(venta);
    }

    // Devuelve ventas por cliente como DTOs
    public List<VentaDTORespuesta> getVentasByCliente(Long clienteId) {
        return ventaRepository.findByClienteId(clienteId).stream()
                .map(this::convertirADTORespuesta)
                .collect(Collectors.toList());
    }

    // Devuelve ventas por producto como DTOs
    public List<VentaDTORespuesta> getVentasByProducto(Long productoId) {
        return ventaRepository.findByProductoId(productoId).stream()
                .map(this::convertirADTORespuesta)
                .collect(Collectors.toList());
    }

    // Devuelve ventas por cliente y producto como DTOs
    public List<VentaDTORespuesta> getVentasPorClienteYProducto(Long clienteId, Long productoId) {
        return ventaRepository.findByClienteIdAndProductoId(clienteId, productoId).stream()
                .map(this::convertirADTORespuesta)
                .collect(Collectors.toList());
    }

    // Devuelve ventas por fecha específica como DTOs
    public List<VentaDTORespuesta> getVentasPorFecha(LocalDateTime fecha) {
        return ventaRepository.findByFechaVenta(fecha).stream()
                .map(this::convertirADTORespuesta)
                .collect(Collectors.toList());
    }

    // Método auxiliar para convertir Venta a DTORespuesta
    private VentaDTORespuesta convertirADTORespuesta(Venta venta) {
        return new VentaDTORespuesta(
                venta.getId(),
                venta.getCantidad(),
                venta.getFechaVenta(),
                venta.getCliente().getId(),
                venta.getCliente().getNombre(),
                venta.getProducto().getId(),
                venta.getProducto().getNombre()
        );
    }

    // Método para registrar venta (ya lo tienes bien hecho)
    @Transactional
    public VentaDTORespuesta registrarVenta(VentaDTO ventaDTO) {

        Cliente cliente = clienteRepository.findById(ventaDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Producto producto = productoRepository.findById(ventaDTO.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() < ventaDTO.getCantidad()) {
            throw new RuntimeException("No hay suficiente stock");
        }

        producto.setStock(producto.getStock() - ventaDTO.getCantidad());
        productoRepository.save(producto);

        Venta nuevaVenta = new Venta();
        nuevaVenta.setCliente(cliente);
        nuevaVenta.setProducto(producto);
        nuevaVenta.setCantidad(ventaDTO.getCantidad());
        nuevaVenta.setFechaVenta(LocalDateTime.now());

        Venta ventaGuardada = ventaRepository.save(nuevaVenta);

        return new VentaDTORespuesta(
                ventaGuardada.getId(),
                ventaGuardada.getCantidad(),
                ventaGuardada.getFechaVenta(),
                cliente.getId(),
                cliente.getNombre(),
                producto.getId(),
                producto.getNombre()
        );
    }

    @Transactional
    public Venta saveVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Transactional
    public void deleteVenta(Long id) {
        ventaRepository.deleteById(id);
    }
}