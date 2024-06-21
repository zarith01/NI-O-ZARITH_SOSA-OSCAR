package com.backend.controller;

import com.backend.dto.entrada.PacienteEntradaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
@CrossOrigin
public class PacienteController {

    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //POST
    @PostMapping("/registrar")
    public PacienteSalidaDto registarPaciente(@RequestBody PacienteEntradaDto pacienteEntradaDto) {
        return pacienteService.guardarPaciente(pacienteEntradaDto);
    }

    //GET
    @GetMapping("/listar")
    public List<PacienteSalidaDto> listarPacientes() {
        return pacienteService.listarTodosLosPacientes();
    }

    @GetMapping("/{id}")
    public PacienteSalidaDto buscarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        return pacienteService.buscarPaciente(id);
    }

    @PutMapping("/{id}")
    public PacienteSalidaDto actualizarPaciente(@PathVariable Long id, @RequestBody PacienteEntradaDto pacienteEntradaDto) throws ResourceNotFoundException {
        return pacienteService.actualizarPaciente(id, pacienteEntradaDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
    }
}