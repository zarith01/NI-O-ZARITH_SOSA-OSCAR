package com.backend.service.impl;

import com.backend.dto.entrada.TurnoEntradaDto;
import com.backend.dto.salida.DomicilioSalidaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.dto.salida.TurnoSalidaDto;
import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.backend.entity.Turno;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.repository.TurnoRepository;
import com.backend.service.ITurnoService;
import com.backend.utils.JsonPrinter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TurnosService implements ITurnoService {

    private static final Logger LOGGER = Logger.getLogger(TurnosService.class);
    private final TurnoRepository turnoRepository;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TurnosService(TurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoSalidaDto buscarTurno(Long id) throws ResourceNotFoundException {
        Turno turno = turnoRepository.findById(id).orElse(null);
        if (turno == null) {
            throw new ResourceNotFoundException("No existe el registro con turno id: " + id);
        }
        LOGGER.info("Turno encontrado con éxito: " + JsonPrinter.toString(turno));
        return convertToDto(turno);
    }

    @Override
    @Transactional
    public TurnoSalidaDto guardarTurno(TurnoEntradaDto turnoEntradaDto) throws ResourceNotFoundException {
        Turno turnoAGuardar = new Turno();
        turnoAGuardar.setFechaYHora(turnoEntradaDto.getFechaYHora());

        OdontologoSalidaDto odontologoDto = odontologoService.buscarOdontologo(turnoEntradaDto.getOdontologoId());

        PacienteSalidaDto pacienteDto = pacienteService.buscarPaciente(turnoEntradaDto.getPacienteId());

        Odontologo odontologo = modelMapper.map(odontologoDto, Odontologo.class);
        Paciente paciente = modelMapper.map(pacienteDto, Paciente.class);

        odontologo = entityManager.merge(odontologo);
        paciente = entityManager.merge(paciente);

        turnoAGuardar.setOdontologo(odontologo);
        turnoAGuardar.setPaciente(paciente);

        Turno turnoGuardado = turnoRepository.save(turnoAGuardar);
        LOGGER.info("Turno guardado con éxito: " + JsonPrinter.toString(turnoGuardado));
        return convertToDto(turnoGuardado);
    }


    @Override
    public List<TurnoSalidaDto> listarTodosLosTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();

        LOGGER.info("Listado de todos los turnos: {}" + JsonPrinter.toString(turnos));

        if (turnos.isEmpty()) {
            LOGGER.warn("No se encontraron turnos");
        }
        return turnos;
    }

    @Override
    public TurnoSalidaDto actualizarTurno(Long id, TurnoEntradaDto turnoEntradaDto) throws ResourceNotFoundException {
        Turno turnoExistente = turnoRepository.findById(id).orElse(null);
        if (turnoExistente == null) {
            throw new ResourceNotFoundException("No existe el registro con turno id: " + id);
        }

        turnoExistente.setFechaYHora(turnoEntradaDto.getFechaYHora());

        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologo(turnoEntradaDto.getOdontologoId());

        turnoExistente.setOdontologo(modelMapper.map(odontologo, Odontologo.class));

        PacienteSalidaDto paciente = pacienteService.buscarPaciente(turnoEntradaDto.getPacienteId());

        turnoExistente.setPaciente(modelMapper.map(paciente, Paciente.class));

        turnoExistente = turnoRepository.save(turnoExistente);
        LOGGER.info("Turno actualizado con éxito: " + JsonPrinter.toString(turnoExistente));
        return convertToDto(turnoExistente);
    }


    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (turnoRepository.existsById(id)) {
            turnoRepository.deleteById(id);
        } else {
            LOGGER.warn("No se encontró el turno con ID: " + id);
            throw new ResourceNotFoundException("No existe el registro con turno id: " + id);
        }
    }

    private TurnoSalidaDto convertToDto(Turno turno) {
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(turno.getOdontologo(), OdontologoSalidaDto.class);
        DomicilioSalidaDto domicilioSalidaDto = modelMapper.map(turno.getPaciente().getDomicilio(), DomicilioSalidaDto.class);
        PacienteSalidaDto pacienteSalidaDto = modelMapper.map(turno.getPaciente(), PacienteSalidaDto.class);
        pacienteSalidaDto.setDomicilioSalidaDto(domicilioSalidaDto);

        turnoSalidaDto.setOdontologoSalidaDto(odontologoSalidaDto);
        turnoSalidaDto.setPacienteSalidaDto(pacienteSalidaDto);

        return turnoSalidaDto;
    }

}
