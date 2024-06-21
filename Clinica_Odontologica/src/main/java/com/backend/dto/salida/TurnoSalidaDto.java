package com.backend.dto.salida;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TurnoSalidaDto {
    private Long id;
    private LocalDateTime fechaYHora;
    private OdontologoSalidaDto odontologoSalidaDto;
    private PacienteSalidaDto pacienteSalidaDto;

    public TurnoSalidaDto(Long id, LocalDateTime fechaYHora, OdontologoSalidaDto odontologoSalidaDto, PacienteSalidaDto pacienteSalidaDto) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologoSalidaDto = odontologoSalidaDto;
        this.pacienteSalidaDto = pacienteSalidaDto;
    }
}
