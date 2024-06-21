package com.backend.repository.impl;

import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoCollection implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoCollection.class);
    private List<Odontologo> odontologos = new ArrayList<>();

    @Override
    public void registrar(Odontologo odontologo) {
        odontologos.add(odontologo);
        logger.info("Odontólogo registrado: " + odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        logger.info("Listado de todos los odontólogos: " + odontologos);
        return odontologos;
    }
}
