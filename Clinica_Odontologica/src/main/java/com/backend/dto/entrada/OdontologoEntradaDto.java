package com.backend.dto.entrada;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OdontologoEntradaDto {

    private Long numMatricula;
    private String nombre;
    private String apellido;

    public OdontologoEntradaDto(Long numMatricula, String nombre, String apellido) {
        this.numMatricula = numMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
