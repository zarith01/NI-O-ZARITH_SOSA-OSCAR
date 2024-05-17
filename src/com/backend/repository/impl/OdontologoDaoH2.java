package com.backend.repository.impl;

import com.backend.dbconnection.H2Connection;
import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public void registrar(Odontologo odontologo) {
        String query = "INSERT INTO odontologos (numeroMatricula, nombre, apellido) VALUES (?, ?, ?)";
        try (Connection connection = H2Connection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, odontologo.getNumeroMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.executeUpdate();
            logger.info("Odontólogo registrado: " + odontologo);
        } catch (Exception e) {
            logger.error("Error al registrar odontólogo", e);
        }
    }

    @Override
    public List<Odontologo> listarTodos() {
        List<Odontologo> odontologos = new ArrayList<>();
        String query = "SELECT * FROM odontologos";
        try (Connection connection = H2Connection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Odontologo odontologo = new Odontologo(
                        rs.getInt("numeroMatricula"),
                        rs.getString("nombre"),
                        rs.getString("apellido"));
                odontologos.add(odontologo);
            }
            logger.info("Listado de todos los odontólogos: " + odontologos);
        } catch (Exception e) {
            logger.error("Error al listar odontólogos", e);
        }
        return odontologos;
    }
}
