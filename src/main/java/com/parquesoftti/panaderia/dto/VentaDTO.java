package com.parquesoftti.panaderia.dto;

import lombok.Data;

@Data
public class VentaDTO {
    private Long clienteId;
    private Long productoId;
    private Integer cantidad;
}