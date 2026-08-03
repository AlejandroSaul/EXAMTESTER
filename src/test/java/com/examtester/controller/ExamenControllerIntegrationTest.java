package com.examtester.controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExamenControllerIntegrationTest {

    private static final int ID_PREGUNTA_PRUEBA = 9999;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    // El DAO usa JDBC crudo (dataSource.getConnection() con autocommit), por lo que
    // @Transactional no haría rollback del INSERT. La limpieza debe ser explícita.
    @AfterEach
    public void limpiarRegistroDePrueba() throws Exception {
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "DELETE FROM PREGUNTAS WHERE ID_PREGUNTA = " + ID_PREGUNTA_PRUEBA)) {
            ps.executeUpdate();
        }
    }

    @Test
    public void testCargaMasivaExcel_EndToEnd() throws Exception {
        // 1. Crear el libro de Excel en memoria (hoja 0)
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();

            // Fila 0 = encabezado (BiImpl la omite, empieza en fila 1)
            sheet.createRow(0);

            // Fila 1 = datos
            Row row = sheet.createRow(1);
            row.createCell(0).setCellValue(ID_PREGUNTA_PRUEBA);      // ID_PREGUNTA (alto para no chocar)
            row.createCell(1).setCellValue(1);                 // ID_SUBTEMA_TOPICO (existe en BD)
            row.createCell(2).setCellValue(1);                 // ID_ORIGEN (obligatorio, FK -> ORIGEN 1)
            row.createCell(3).setCellValue("Pregunta de Integración"); // PREGUNTA
            row.createCell(5).setCellValue("A");               // RESPUESTA_A
            row.createCell(6).setCellValue("B");               // RESPUESTA_B
            row.createCell(14).setCellValue("B");              // RESPUESTA_CORRECTA (obligatoria)

            // 2. Escribir el libro en un ByteArrayOutputStream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);

            // 3. Construir el MockMultipartFile
            MockMultipartFile file = new MockMultipartFile(
                    "file",
                    "prueba.xlsx",
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                    baos.toByteArray());

            // 4. Enviar la petición al endpoint de carga masiva
            mockMvc.perform(multipart("/api/examen/importar-excel").file(file))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.mensaje", containsString("Se insertaron 1 registros")));
        }
    }
}
