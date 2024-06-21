package com.backend.controller;

import com.backend.dto.entrada.TurnoEntradaDto;
import com.backend.dto.salida.TurnoSalidaDto;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("turnos")
@CrossOrigin
public class TurnoController {

    private ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //POST
    @PostMapping("/registrar")
    public TurnoSalidaDto registarTurno(@RequestBody TurnoEntradaDto turnoEntradaDto) throws ResourceNotFoundException {
        return turnoService.guardarTurno(turnoEntradaDto);
    }

    //GET
    @GetMapping("/listar")
    public List<TurnoSalidaDto> listarTurnos() {
        return turnoService.listarTodosLosTurnos();
    }

    @GetMapping("/{id}")
    public TurnoSalidaDto buscarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        return turnoService.buscarTurno(id);
    }

    @PutMapping("/{id}")
    public TurnoSalidaDto actualizarTurno(@PathVariable Long id, @RequestBody TurnoEntradaDto turnoEntradaDto) throws ResourceNotFoundException {
        return turnoService.actualizarTurno(id, turnoEntradaDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
    }
}