package com.parquesoftti.panaderia.repository;

import com.parquesoftti.panaderia.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Métodos para buscar el cliente, el producto, ambos y rango de fechas de venta

    // Busca todas las ventas de un cliente
    List<Venta> findByClienteId(Long clienteId);

    // Busca todas las ventas de un producto
    List<Venta> findByProductoId(Long productoId);

    // Busca ventas por cliente y producto
    List<Venta> findByClienteIdAndProductoId(Long clienteId, Long productoId);

    // Busca ventas por fecha
    List<Venta> findByFechaVenta(LocalDateTime fecha);

}
