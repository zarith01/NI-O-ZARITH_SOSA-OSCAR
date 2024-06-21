package com.backend.service.impl;

import com.backend.dto.entrada.PacienteEntradaDto;
import com.backend.dto.salida.DomicilioSalidaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.entity.Paciente;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.repository.PacienteRepository;
import com.backend.service.IPacienteService;
import com.backend.utils.JsonPrinter;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    private final ModelMapper modelMapper;
    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PacienteSalidaDto buscarPaciente(Long id) throws ResourceNotFoundException {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        if (paciente == null) {
            throw new ResourceNotFoundException("No existe registro de paciente con id " + id);
        }

        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(paciente, PacienteSalidaDto.class);
        DomicilioSalidaDto domicilioSalidaDto = modelMapper.map(paciente.getDomicilio(), DomicilioSalidaDto.class);
        pacienteSalidaDto.setDomicilioSalidaDto(domicilioSalidaDto);
        LOGGER.info("Paciente encontrado con éxito: " + JsonPrinter.toString(pacienteSalidaDto));
        return pacienteSalidaDto;
    }


    @Override
    public PacienteSalidaDto guardarPaciente(PacienteEntradaDto pacienteEntradaDto) {
        Paciente paciente = modelMapper.map(pacienteEntradaDto, Paciente.class);
        paciente = pacienteRepository.save(paciente);

        PacienteSalidaDto pacienteSalidaDto = convertToDto(paciente);
        LOGGER.info("Paciente guardado con éxito: " + JsonPrinter.toString(pacienteSalidaDto));

        return pacienteSalidaDto;
    }


    @Override
    public List<PacienteSalidaDto> listarTodosLosPacientes() {
        List<PacienteSalidaDto> pacientes = pacienteRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();

        LOGGER.info("Listado de todos los pacientes: {}" + JsonPrinter.toString(pacientes));

        if (pacientes.isEmpty()) {
            LOGGER.warn("No se encontraron pacientes");
        }
        return pacientes;
    }

    @Override
    public PacienteSalidaDto actualizarPaciente(Long id, PacienteEntradaDto pacienteEntradaDto) throws ResourceNotFoundException {
        Paciente pacienteExistente = pacienteRepository.findById(id).orElse(null);
        if (pacienteExistente == null) {
            throw new ResourceNotFoundException("No existe registro de paciente con id " + id);
        }

        modelMapper.map(pacienteEntradaDto, pacienteExistente);
        pacienteExistente = pacienteRepository.save(pacienteExistente);

        PacienteSalidaDto pacienteSalidaDto = convertToDto(pacienteExistente);
        LOGGER.info("Paciente actualizado con éxito: " + JsonPrinter.toString(pacienteSalidaDto));

        return pacienteSalidaDto;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
        } else {
            LOGGER.warn("No se encontró el paciente con ID: " + id);
            throw new ResourceNotFoundException("No existe registro de paciente con id " + id);
        }
    }

    private PacienteSalidaDto convertToDto(Paciente paciente) {
        DomicilioSalidaDto domicilioSalidaDto = modelMapper.map(paciente.getDomicilio(), DomicilioSalidaDto.class);
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(paciente, PacienteSalidaDto.class);
        pacienteSalidaDto.setDomicilioSalidaDto(domicilioSalidaDto);
        return pacienteSalidaDto;
    }
}
