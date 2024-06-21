package com.backend.controller;

import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/odontologos")
@CrossOrigin
public class OdontologoController {

    private final IOdontologoService odontologoService;

    @Autowired
    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    // POST
    @PostMapping("/registrar")
    public OdontologoSalidaDto registrarOdontologo(@RequestBody OdontologoEntradaDto odontologoEntradaDto) {
        return odontologoService.guardarOdontologo(odontologoEntradaDto);
    }

    // GET
    @GetMapping("/listar")
    public List<OdontologoSalidaDto> listarOdontologos() {
        return odontologoService.listarTodosLosOdontologos();
    }

    @GetMapping("/{id}")
    public OdontologoSalidaDto buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        return odontologoService.buscarOdontologo(id);
    }

    @PutMapping("/{id}")
    public OdontologoSalidaDto actualizarOdontologo(@PathVariable Long id, @RequestBody OdontologoEntradaDto odontologoEntradaDto) throws ResourceNotFoundException {
        return odontologoService.actualizarOdontologo(id, odontologoEntradaDto);
    }

    @DeleteMapping("/{id}")
    public void eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
    }
}
