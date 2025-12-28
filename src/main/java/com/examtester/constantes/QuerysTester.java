package com.examtester.constantes;

public class QuerysTester {
	public static final String QUERY_ALL_FROM_PREGUNTAS_ID = "SELECT * FROM PREGUNTAS WHERE ID_PREGUNTA = ?";
	public static final String QUERY_COUNT_PREGUNTAS = "SELECT COUNT(*) AS TOTAL_PREGUNTAS FROM PREGUNTAS";
	public static final String QUERY_GET_TEMAS = "SELECT * FROM TEMA";
	public static final String QUERY_GET_SUBTEMAS = "SELECT * FROM SUBTEMA";
	public static final String QUERY_GET_TOPICO = "SELECT * FROM TOPICO";
	public static final String QUERY_GET_ALL_PREGUNTAS = "SELECT "
		    + "P.ID_PREGUNTA, "
		    + "P.PREGUNTA, "
		    + "P.IMAGEN_PREGUNTA, "
		    + "P.RESPUESTA_A, "
		    + "P.IMAGEN_RESP_A, "
		    + "P.RESPUESTA_B, "
		    + "P.IMAGEN_RESP_B, "
		    + "P.RESPUESTA_C, "
		    + "P.IMAGEN_RESP_C, "
		    + "P.RESPUESTA_D, "
		    + "P.IMAGEN_RESP_D, "
		    + "P.RESPUESTA_CORRECTA, "
		    + "ORI.ORIGEN, "
		    + "S.NOMBRE_SUBTEMA, "
		    + "T.NOMBRE_TEMA, "
		    + "TOP.NOMBRE_TOPICO, "
		    + "M.NOMBRE_MATERIA, "
		    + "P.UNIDAD, "
		    + "P.EXPLICACION, "
		    + "P.IMAGEN_EXPLICA "
		    + "FROM preguntas P "
		    + "JOIN origen ORI ON ORI.ID_ORIGEN = P.ID_ORIGEN "
		    + "JOIN subtema_topico ST ON ST.ID_SUBTEMA_TOPICO = P.ID_SUBTEMA_TOPICO "
		    + "JOIN subtema S ON ST.ID_SUBTEMA = S.ID_SUBTEMA "
		    + "JOIN TEMA T ON T.ID_TEMA = S.ID_TEMA "
		    + "JOIN TOPICO TOP ON TOP.ID_TOPICO = ST.ID_TOPICO "
		    + "LEFT JOIN MATERIA M ON P.ID_MATERIA = M.ID_MATERIA;";
	
	public static final String QUERY_GET_PREGUNTA = "SELECT "
		    + "P.ID_PREGUNTA, "
		    + "P.PREGUNTA, "
		    + "P.IMAGEN_PREGUNTA, "
		    + "P.RESPUESTA_A, "
		    + "P.IMAGEN_RESP_A, "
		    + "P.RESPUESTA_B, "
		    + "P.IMAGEN_RESP_B, "
		    + "P.RESPUESTA_C, "
		    + "P.IMAGEN_RESP_C, "
		    + "P.RESPUESTA_D, "
		    + "P.IMAGEN_RESP_D, "
		    + "P.RESPUESTA_CORRECTA, "
		    + "ORI.ORIGEN, "
		    + "S.NOMBRE_SUBTEMA, "
		    + "T.NOMBRE_TEMA, "
		    + "TOP.NOMBRE_TOPICO, "
		    + "M.NOMBRE_MATERIA, "
		    + "P.UNIDAD "
		    + "FROM preguntas P "
		    + "JOIN origen ORI ON ORI.ID_ORIGEN = P.ID_ORIGEN "
		    + "JOIN subtema_topico ST ON ST.ID_SUBTEMA_TOPICO = P.ID_SUBTEMA_TOPICO "
		    + "JOIN subtema S ON ST.ID_SUBTEMA = S.ID_SUBTEMA "
		    + "JOIN TEMA T ON T.ID_TEMA = S.ID_TEMA "
		    + "JOIN TOPICO TOP ON TOP.ID_TOPICO = ST.ID_TOPICO "
		    + "LEFT JOIN MATERIA M ON P.ID_MATERIA = M.ID_MATERIA "
		    + "WHERE P.ID_PREGUNTA = ?;";
}


