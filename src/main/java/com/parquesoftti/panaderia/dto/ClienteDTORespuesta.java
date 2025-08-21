package com.parquesoftti.panaderia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTORespuesta {
    private Long id;
    private String nombre;
    private String telefono;
}