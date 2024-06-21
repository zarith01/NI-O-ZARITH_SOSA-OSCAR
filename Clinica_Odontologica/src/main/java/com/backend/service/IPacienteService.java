package com.backend.service;

import com.backend.dto.entrada.PacienteEntradaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPacienteService {
    PacienteSalidaDto buscarPaciente(Long id) throws ResourceNotFoundException;

    PacienteSalidaDto guardarPaciente(PacienteEntradaDto pacienteEntradaDto);

    List<PacienteSalidaDto> listarTodosLosPacientes();

    PacienteSalidaDto actualizarPaciente(Long id, PacienteEntradaDto pacienteEntradaDto) throws ResourceNotFoundException;

    void eliminarPaciente(Long id) throws ResourceNotFoundException;
}
