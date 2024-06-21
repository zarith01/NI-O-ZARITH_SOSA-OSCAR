package com.backend.dto.entrada;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TurnoEntradaDto {
    private Long id;
    private LocalDateTime fechaYHora;
    private Long odontologoId;
    private Long pacienteId;

    public TurnoEntradaDto(LocalDateTime fechaYHora, Long odontologoId, Long pacienteId) {
        this.fechaYHora = fechaYHora;
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
    }
}
