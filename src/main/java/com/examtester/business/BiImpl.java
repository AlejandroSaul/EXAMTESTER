package com.examtester.business;

import java.rmi.server.LoaderHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
	public PreguntaInfoVo getPregunta(Long id) {
		PreguntaInfoVo pregunta = examenDAO.getPregunta(id); 
		return pregunta;
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
	public List<String> getRespuestasPosibles(Pregunta pregunta) {
		List<String> respuestasPosibles = new ArrayList<String>();
		respuestasPosibles.add(pregunta.getRespuestaA());
		respuestasPosibles.add(pregunta.getRespuestaB());
		respuestasPosibles.add(pregunta.getRespuestaC());
		respuestasPosibles.add(pregunta.getRespuestaD());
		return respuestasPosibles;
	}
	
	@Override
	public String getRespuesta(Pregunta pregunta) {
		return pregunta.getRespuestaCorrecta();
	}

	@Override
	public Integer getId(Pregunta pregunta) {
		return pregunta.getIdPregunta();
	}

	@Override
	public String getExplicacion(Pregunta pregunta) {
		return pregunta.getExplicacion();
	}

	@Override
	public List<PreguntaInfoVo> getAllPreguntas() {
		List<PreguntaInfoVo> preguntas = examenDAO.getAllPreguntas(); 
		return preguntas;
	}
	

	
	@Override
	public GenericResponse insertarPregunta(Pregunta pregunta) {
		GenericResponse  respuesta = new GenericResponse(); 
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
		return respuesta;
	}
	
	@Override
	public GenericResponse procesarExcelPreguntas(MultipartFile file) {
		GenericResponse respuesta = new GenericResponse();
		int filasInsertadas = 0;

		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);

				if (row == null || isRowEmpty(row)) continue;

				try {
					Pregunta pregunta = new Pregunta();
					pregunta.setIdPregunta((int)getNumericValue(row.getCell(0)));
					pregunta.setIdSubtemaTopico((int) getNumericValue(row.getCell(1)));
					pregunta.setIdOrigen((int) getNumericValue(row.getCell(2)));
					pregunta.setPregunta(getStringValue(row.getCell(3)));
					pregunta.setRutaArchivo(getStringValue(row.getCell(4)));
					pregunta.setRespuestaA(getStringValue(row.getCell(5)));
					pregunta.setRespuestaB(getStringValue(row.getCell(6)));
					pregunta.setRespuestaC(getStringValue(row.getCell(7)));
					pregunta.setRespuestaD(getStringValue(row.getCell(8)));
					pregunta.setRespuestaE(getStringValue(row.getCell(9)));
					pregunta.setRespuestaF(getStringValue(row.getCell(10)));
					pregunta.setRespuestaG(getStringValue(row.getCell(11)));
					pregunta.setRespuestaH(getStringValue(row.getCell(12)));
					pregunta.setRespuestaI(getStringValue(row.getCell(13)));
					pregunta.setRespuestaCorrecta(getStringValue(row.getCell(14)));
					pregunta.setExplicacion(getStringValue(row.getCell(15)));
					pregunta.setIdMateria((int) getNumericValue(row.getCell(16)));
					pregunta.setUnidad(getStringValue(row.getCell(17)));

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
			respuesta.setCodigo(0); // Código 0 para errores
			respuesta.setMensaje("Error al procesar el archivo: " + e.getMessage());
		}
		return respuesta;
	}
	
	private String getStringValue(Cell cell) {
	    if (cell == null || cell.getCellType() == CellType.BLANK) return "";
	    if (cell.getCellType() == CellType.NUMERIC) {
	        // En caso de que un número se lea como texto (ej. ID de materia)
	        return String.valueOf((int) cell.getNumericCellValue());
	    }
	    return cell.getStringCellValue().trim();
	}

	private double getNumericValue(Cell cell) {
	    if (cell == null || cell.getCellType() == CellType.BLANK) return 0;
	    if (cell.getCellType() == CellType.STRING) {
	        try { return Double.parseDouble(cell.getStringCellValue()); } 
	        catch (NumberFormatException e) { return 0; }
	    }
	    return cell.getNumericCellValue();
	}

	private boolean isRowEmpty(Row row) {
	    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
	        Cell cell = row.getCell(c);
	        if (cell != null && cell.getCellType() != CellType.BLANK) return false;
	    }
	    return true;
	}

}
