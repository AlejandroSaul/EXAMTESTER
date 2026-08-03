package com.examtester.dao;

import com.examtester.entidad.Pregunta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExamenDAOImplTest {

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @InjectMocks
    private ExamenDAOImpl examenDAO;

    @Test
    public void testFormatoPrefijosRespuestas() throws Exception {
        // 1. Preparar mocks
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        // 2. Preparar datos
        Pregunta pregunta = new Pregunta();
        pregunta.setIdPregunta(1);
        pregunta.setIdSubtemaTopico(1);
        pregunta.setIdOrigen(1);
        pregunta.setPregunta("¿Cuál es la capital de Francia?");
        pregunta.setRespuestaCorrecta("B");
        pregunta.setRespuestaA("Madrid");
        pregunta.setRespuestaB("París");

        // 3. Ejecutar método real de inserción del DAO
        examenDAO.insertarPregunta(pregunta);

        // 4. Capturar e inspeccionar los argumentos enviados a JDBC
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        verify(preparedStatement, atLeastOnce()).setString(anyInt(), stringCaptor.capture());

        // 5. Validar que el prefijo fue inyectado (el DAO usa la letra de la respuesta)
        boolean tienePrefijoA = stringCaptor.getAllValues().stream().anyMatch(v -> v.equals("A.- Madrid"));
        boolean tienePrefijoB = stringCaptor.getAllValues().stream().anyMatch(v -> v.equals("B.- París"));

        assertTrue(tienePrefijoA, "El DAO debe agregar el prefijo A.- a la respuesta A");
        assertTrue(tienePrefijoB, "El DAO debe agregar el prefijo B.- a la respuesta B");
    }
}
