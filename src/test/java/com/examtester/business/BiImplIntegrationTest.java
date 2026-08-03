package com.examtester.business;


import com.examtester.entidad.PreguntaInfoVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class BiImplIntegrationTest {


    @Autowired
    private Bi bi;


    @Test
    public void testRecuperacionEhidratacionPregunta() {
        // 1. Definir un ID de subtema/tópico que sepamos que existe en tu BD local.
        // En esta BD el tópico 1 (ID_SUBTEMA_TOPICO=1) es el de preguntas de Java
        // y contiene varias (IDs de pregunta 1..21), así que es seguro usarlo.
        Long idSubtemaTopico = 1L;


        // 2. Ejecutar la consulta real que atraviesa BiImpl -> ExamenDAOImpl -> MySQL
        PreguntaInfoVo pregunta = bi.getPreguntaXSubtemaTopico(idSubtemaTopico);


        // 3. Validar que la hidratación (mapeo) del objeto fue exitosa
        assertNotNull(pregunta, "El objeto recuperado de la BD no debe ser nulo");
        assertNotNull(pregunta.getPregunta(), "El texto de la pregunta debe venir mapeado desde MySQL");
        assertNotNull(pregunta.getRespuestaA(), "La respuesta A debe mapearse correctamente");
        assertNotNull(pregunta.getRespuestaB(), "La respuesta B debe mapearse correctamente");
    }
}
