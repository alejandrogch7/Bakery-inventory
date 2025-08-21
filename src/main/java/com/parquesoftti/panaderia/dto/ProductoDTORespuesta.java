package com.parquesoftti.panaderia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTORespuesta {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
}