package com.backend.dto.salida;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PacienteSalidaDto {
    private Long id;
    private Long dni;
    private String nombre;
    private String apellido;
    private DomicilioSalidaDto domicilioSalidaDto;
    private LocalDate fechaAlta;

    public PacienteSalidaDto(Long id, Long dni, String nombre, String apellido, DomicilioSalidaDto domicilioSalidaDto, LocalDate fechaAlta) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilioSalidaDto = domicilioSalidaDto;
        this.fechaAlta = fechaAlta;
    }
}
