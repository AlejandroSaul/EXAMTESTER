package com.examtester.business;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase de utilidades para procesar celdas de un archivo Excel.
 * No debe instanciarse: todos los métodos son estáticos.
 *
 * @author Alejandro Saúl Baños
 */
public final class BiProcesamientoExcel {

	private static final Logger logger = LoggerFactory.getLogger(BiProcesamientoExcel.class);

	private BiProcesamientoExcel() {
		// Constructor privado para evitar instanciación
	}
	
	/**
	 * Método que asegura la conversión de tipo de dato a String
	 * @param cell objeto de tipo celda que contiene el valor a castear
	 * @return String cadena de texto del contenido de la celda
	 * @author Alejandro Saúl Baños
	 * */
	public static String getStringValue(Cell cell) {
		try {
			if (cell == null || cell.getCellType() == CellType.BLANK) {
				return "";
			}
			if (cell.getCellType() == CellType.NUMERIC) {
				// En caso de que un número se lea como texto (ej. ID de materia)
				return String.valueOf((int) cell.getNumericCellValue());
			}
			return cell.getStringCellValue().trim();
		} catch (Exception e) {
			logger.error("Error leyendo celda como texto...", e);
			return "";
		}
	}
	
	/**
	 * Método que asegura la conversión de tipo de dato a double
	 * @param cell objeto de tipo celda que contiene el valor a castear
	 * @return double valor del contenido de la celda
	 * @author Alejandro Saúl Baños
	 * */
	public static double getNumericValue(Cell cell) {
		if (cell == null || cell.getCellType() == CellType.BLANK) return 0;
		try {
			if (cell.getCellType() == CellType.STRING) {
				try { 
					return Double.parseDouble(cell.getStringCellValue()); 
				} 
				catch (NumberFormatException e) {
					logger.error("Error al parsear celda numérica: {}", e.getMessage(), e);
					return 0; 
				}
			}
			return cell.getNumericCellValue();
		} catch (Exception e) {
			logger.error("Error leyendo celda como numérico...", e);
			return 0;
		}
	}
	
	/**
	 * Método que valida que la fila existe y dentro de la fila existan datos
	 * @param row fila evaluada
	 * @return boolean true si existen datos dentro de la fila false si no eixte nada en la fila
	 * @author Alejandro Saúl Baños
	 * */
	public static boolean isRowEmpty(Row row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != CellType.BLANK) return false;
		}
		return true;
	}
}
