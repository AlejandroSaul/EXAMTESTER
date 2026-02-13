package com.examtester.constantes;

public class QuerysTester {
	public static final String QUERY_ALL_FROM_PREGUNTAS_ID = "SELECT * FROM PREGUNTAS WHERE ID_PREGUNTA = ?";
	public static final String QUERY_COUNT_PREGUNTAS = "SELECT COUNT(*) AS TOTAL_PREGUNTAS FROM PREGUNTAS";
	public static final String QUERY_GET_TEMAS = "SELECT * FROM TEMA";
	public static final String QUERY_GET_SUBTEMAS = "SELECT * FROM SUBTEMA";
	public static final String QUERY_GET_TOPICO = "SELECT * FROM TOPICO";
	public static final String QUERY_GET_SUBTEMAS_ID = "SELECT * FROM SUBTEMA WHERE ID_TEMA = ?";
	public static final String QUERY_GET_TOPICOS = "SELECT sto.ID_SUBTEMA_TOPICO, NOMBRE_SUBTEMA, NOMBRE_TOPICO from subtema_topico sto "
			+ "join subtema st on sto.ID_SUBTEMA = st.ID_SUBTEMA "
			+ "join topico t on sto.ID_TOPICO = t.ID_TOPICO "
			+ "WHERE st.ID_SUBTEMA = ? ";
	public static final String QUERY_GET_PREGUNTAS_SUBTEMATOPICO = "SELECT ID_PREGUNTA FROM PREGUNTAS "
			+ "WHERE ID_SUBTEMA_TOPICO = ? ";
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
		    + "P.RESPUESTA_E, "
		    + "P.IMAGEN_RESP_E, "
		    + "P.RESPUESTA_F, "
		    + "P.IMAGEN_RESP_F, "
		    + "P.RESPUESTA_G, "
		    + "P.IMAGEN_RESP_G, "
		    + "P.RESPUESTA_H, "
		    + "P.IMAGEN_RESP_H, "
		    + "P.RESPUESTA_I, "
		    + "P.IMAGEN_RESP_I, "
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
		    + "LEFT JOIN MATERIA M ON P.ID_MATERIA = M.ID_MATERIA "
		    + "WHERE P.ID_PREGUNTA = ?;";
}


