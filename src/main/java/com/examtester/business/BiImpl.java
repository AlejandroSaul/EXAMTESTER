package com.examtester.business;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.examtester.dao.ExamenDAO;
import com.examtester.entidad.GenericResponse;
import com.examtester.entidad.Pregunta;
import com.examtester.entidad.PreguntaInfoVo;

@Service
public class BiImpl implements Bi{
	private final ExamenDAO examenDAO;

	public BiImpl(ExamenDAO examenDAO) {
		this.examenDAO = examenDAO;
	}

	@Override
	public Map<Integer,String> getTemas(){
		Map<Integer,String> temas = examenDAO.getTemas();
		return temas;
	}

	@Override
	public Map<Integer,String> getSubtemas(Long idTema){
		Map<Integer,String> temas = examenDAO.getSubtemas(idTema);
		return temas;
	}


	@Override
	public Map<Integer,String> getTopicos(Long idSubtema) {		
		return examenDAO.getTopicos(idSubtema);
	}

	@Override
	public PreguntaInfoVo getPreguntaXSubtemaTopico(Long idSubtemaTopico) {
		ArrayList<Integer> arregloPreguntas = examenDAO.getPreguntasXSubtemaTopico(idSubtemaTopico);
		Integer longitud  = arregloPreguntas.size();
		Random random = new Random();
		Integer posicionAleatoria = random.nextInt(0, longitud-1) ;
		Long idPregunta =  Long.parseLong(arregloPreguntas.get(posicionAleatoria).toString());
		return getPregunta(idPregunta);
	}

	@Override
	public GenericResponse procesarExcelPreguntas(MultipartFile file) {
		GenericResponse respuesta = new GenericResponse();
		int filasInsertadas = 0;
		
		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0);
			
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				
				if (row == null || BiProcesamientoExcel.isRowEmpty(row)) continue;
				
				try {
					Pregunta pregunta = new Pregunta();
					pregunta.setIdPregunta((int)BiProcesamientoExcel.getNumericValue(row.getCell(0)));
					pregunta.setIdSubtemaTopico((int) BiProcesamientoExcel.getNumericValue(row.getCell(1)));
					pregunta.setIdOrigen((int) BiProcesamientoExcel.getNumericValue(row.getCell(2)));
					pregunta.setPregunta(BiProcesamientoExcel.getStringValue(row.getCell(3)));
					pregunta.setRutaArchivo(BiProcesamientoExcel.getStringValue(row.getCell(4)));
					pregunta.setRespuestaA(BiProcesamientoExcel.getStringValue(row.getCell(5)));
					pregunta.setRespuestaB(BiProcesamientoExcel.getStringValue(row.getCell(6)));
					pregunta.setRespuestaC(BiProcesamientoExcel.getStringValue(row.getCell(7)));
					pregunta.setRespuestaD(BiProcesamientoExcel.getStringValue(row.getCell(8)));
					pregunta.setRespuestaE(BiProcesamientoExcel.getStringValue(row.getCell(9)));
					pregunta.setRespuestaF(BiProcesamientoExcel.getStringValue(row.getCell(10)));
					pregunta.setRespuestaG(BiProcesamientoExcel.getStringValue(row.getCell(11)));
					pregunta.setRespuestaH(BiProcesamientoExcel.getStringValue(row.getCell(12)));
					pregunta.setRespuestaI(BiProcesamientoExcel.getStringValue(row.getCell(13)));
					pregunta.setRespuestaCorrecta(BiProcesamientoExcel.getStringValue(row.getCell(14)));
					pregunta.setExplicacion(BiProcesamientoExcel.getStringValue(row.getCell(15)));
					pregunta.setIdMateria((int) BiProcesamientoExcel.getNumericValue(row.getCell(16)));
					pregunta.setUnidad(BiProcesamientoExcel.getStringValue(row.getCell(17)));
					
					insertarPregunta(pregunta);
					filasInsertadas++;
				} catch (Exception e) {
					System.out.println("Error en la fila " + i + ": " + e.getMessage());
				}
			}
			respuesta.setCodigo(1);
			respuesta.setMensaje("Proceso terminado. Se insertaron " + filasInsertadas + " registros con éxito.");
		} catch (Exception e) {
			System.out.println("Error general al procesar Excel: "+ e);
			respuesta.setCodigo(0);
			respuesta.setMensaje("Error al procesar el archivo: " + e.getMessage());
		}
		return respuesta;
	}
	
    /**
     * Método que obtiene la pregunta solicitada mediante el id enviado 
     * @param id de la pregunta deseada
     * @return PreguntaInfoVo Objeto de valor con la información de la pregunta
     * @author Alejandro Saúl Baños
     * */
	private PreguntaInfoVo getPregunta(Long id) {
		PreguntaInfoVo pregunta = examenDAO.getPregunta(id); 
		return pregunta;
	}
	
    /**
     * Método que inserta una pregunta en la tabla Preguntas 
     * @param pregunta objeto de tipo Pregunta que contiene todos los datos a insertar
     * @return GenericResponse Objeto con la respuesta generica para obtener información de la inserción
     * @author Alejandro Saúl Baños
     * */
	private GenericResponse insertarPregunta(Pregunta pregunta) {
		GenericResponse  respuesta = new GenericResponse();
		try {
			if (pregunta.getPregunta() != null
					&& pregunta.getIdSubtemaTopico() != null
					&& pregunta.getIdOrigen() != null
					&& pregunta.getRespuestaCorrecta() != null
					&& pregunta.getRespuestaA() != null
					&& pregunta.getRespuestaB() != null) {
				respuesta = examenDAO.insertarPregunta(pregunta);
			} else {
				respuesta.setCodigo(2);
				respuesta.setMensaje("Error al insertar los datos");
			}
		} catch (Exception e) {
			
		}
		return respuesta;
	}

}
