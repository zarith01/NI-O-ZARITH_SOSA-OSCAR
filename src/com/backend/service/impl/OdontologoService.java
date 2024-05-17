package com.backend.service.impl;

import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import com.backend.service.IOdontologoService;

import java.util.List;

public class OdontologoService implements IOdontologoService {
    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoDao) {
        this.odontologoIDao = odontologoDao;
    }

    @Override
    public void registrarOdontologo(Odontologo odontologo) {
        odontologoIDao.registrar(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoIDao.listarTodos();
    }
}