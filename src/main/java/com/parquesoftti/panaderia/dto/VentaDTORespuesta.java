package com.parquesoftti.panaderia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data               // Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor    // Genera constructor vacío
@AllArgsConstructor   // Genera constructor con todos los campos
public class VentaDTORespuesta {

    private Long id;
    private Integer cantidad;
    private LocalDateTime fechaVenta;

    private Long clienteId;
    private String nombreCliente;

    private Long productoId;
    private String nombreProducto;
}