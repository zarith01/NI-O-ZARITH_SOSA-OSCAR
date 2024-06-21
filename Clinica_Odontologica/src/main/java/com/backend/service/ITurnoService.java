package com.backend.service;

import com.backend.dto.entrada.TurnoEntradaDto;
import com.backend.dto.salida.TurnoSalidaDto;
import com.backend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITurnoService {
    TurnoSalidaDto buscarTurno(Long id) throws ResourceNotFoundException;

    TurnoSalidaDto guardarTurno(TurnoEntradaDto turno) throws ResourceNotFoundException;

    List<TurnoSalidaDto> listarTodosLosTurnos();

    TurnoSalidaDto actualizarTurno(Long id, TurnoEntradaDto turnoEntradaDto) throws ResourceNotFoundException;

    void eliminarTurno(Long id) throws ResourceNotFoundException;
}
