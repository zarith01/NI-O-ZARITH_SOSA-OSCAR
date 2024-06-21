package com.backend.service;

import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOdontologoService {
    OdontologoSalidaDto buscarOdontologo(Long id) throws ResourceNotFoundException;

    OdontologoSalidaDto guardarOdontologo(OdontologoEntradaDto odontologoEntradaDto);

    List<OdontologoSalidaDto> listarTodosLosOdontologos();

    OdontologoSalidaDto actualizarOdontologo(Long id, OdontologoEntradaDto odontologoEntradaDto) throws ResourceNotFoundException;

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;
}
