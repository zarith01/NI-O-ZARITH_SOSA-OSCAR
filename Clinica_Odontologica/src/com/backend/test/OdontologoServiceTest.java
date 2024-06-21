package com.backend.test;

import com.backend.entity.Odontologo;
import com.backend.repository.impl.OdontologoDaoCollection;
import com.backend.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class
OdontologoServiceTest {
    private OdontologoService odontologoService;

    @BeforeEach
    public void setup() {
        odontologoService = new OdontologoService(new OdontologoDaoCollection());
    }

    @Test
    public void testListarTodos() {
        Odontologo odontologo1 = new Odontologo(1, "Juan", "Perez");
        Odontologo odontologo2 = new Odontologo(2, "Ana", "Gomez");

        odontologoService.registrarOdontologo(odontologo1);
        odontologoService.registrarOdontologo(odontologo2);

        List<Odontologo> odontologos = odontologoService.listarTodos();

        // Verifica que la lista contiene 2 elementos
        assertEquals(2, odontologos.size(), "La lista debe contener 2 odontólogos");

        // Verifica que los elementos son los esperados
        assertEquals(odontologo1, odontologos.get(0), "El primer odontólogo debe ser Juan Perez");
        assertEquals(odontologo2, odontologos.get(1), "El segundo odontólogo debe ser Ana Gomez");
    }
}