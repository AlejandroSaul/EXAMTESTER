package com.examtester.controller;

import com.examtester.business.Bi;
import com.examtester.entidad.PreguntaInfoVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExamenControllerTest {

    @Mock
    private Bi bi;

    @InjectMocks
    private ExamenController examenController;

    @Test
    public void testObtenerPreguntaAleatoria_Exito() {
        // 1. Preparar los datos simulados (Mock)
        Long idSubtemaTopico = 5L;
        PreguntaInfoVo preguntaMock = new PreguntaInfoVo();
        preguntaMock.setIdPregunta("100");
        preguntaMock.setPregunta("¿Cuál es la capital de Francia?");
        preguntaMock.setRespuestaA("X.- Madrid");
        preguntaMock.setRespuestaB("X.- París");

        // Comportamiento esperado de Mockito
        when(bi.getPreguntaXSubtemaTopico(idSubtemaTopico)).thenReturn(preguntaMock);

        // 2. Ejecutar el método del controlador
        PreguntaInfoVo resultado = examenController.getPreguntaXSubtemaTopico(idSubtemaTopico);

        // 3. Verificar los resultados (Asserts)
        assertNotNull(resultado);
        assertEquals("¿Cuál es la capital de Francia?", resultado.getPregunta());
        assertEquals("X.- París", resultado.getRespuestaB());
    }
}
