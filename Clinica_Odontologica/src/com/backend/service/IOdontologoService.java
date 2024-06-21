package com.backend.service;

import com.backend.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    void registrarOdontologo(Odontologo odontologo);
    List<Odontologo> listarTodos();
}