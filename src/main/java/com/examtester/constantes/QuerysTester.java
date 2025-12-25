package com.examtester.constantes;

public class QuerysTester {
	public static final String QUERY_ALL_FROM_PREGUNTAS_ID = "SELECT * FROM PREGUNTAS WHERE ID_PREGUNTA = ?";
	public static final String QUERY_COUNT_PREGUNTAS = "SELECT COUNT(*) AS TOTAL_PREGUNTAS FROM PREGUNTAS";
	public static final String QUERY_GET_TEMAS = "SELECT * FROM TEMA";
	public static final String QUERY_GET_SUBTEMAS = "SELECT * FROM SUBTEMA";
	public static final String QUERY_GET_TOPICO = "SELECT * FROM TOPICO";
	public static final String QUERY_GET_SUBTEMA_ACTIVO = "SELECT * FROM PREGUNTAS WHERE ID_SUBTEMA_TOPICO IN \r\n"
			+ "(SELECT ID_SUBTEMA_TOPICO FROM SUBTEMA_TOPICO WHERE ID_SUBTEMA IN \r\n"
			+ "(SELECT ID_SUBTEMA FROM SUBTEMA WHERE ESTATUS = 'A'))";
	public static final String QUERY_GET_ALL_PREGUNTAS = "SELECT\r\n"
			+ "	P.ID_PREGUNTA,\r\n"
			+ "	P.PREGUNTA,\r\n"
			+ "	P.IMAGEN,\r\n"
			+ "	P.RESPUESTA_A,\r\n"
			+ "	P.RESPUESTA_B,\r\n"
			+ "	P.RESPUESTA_C,\r\n"
			+ "	P.RESPUESTA_D,\r\n"
			+ "	P.RESPUESTA_CORRECTA,\r\n"
			+ "	ORI.ORIGEN,\r\n"
			+ "	S.NOMBRE_SUBTEMA,\r\n"
			+ "	T.NOMBRE_TEMA,\r\n"
			+ "	TOP.NOMBRE_TOPICO\r\n"
			+ "FROM preguntas P \r\n"
			+ "JOIN origen ORI ON ORI.ID_ORIGEN = P.ID_ORIGEN\r\n"
			+ "JOIN subtema_topico ST ON ST.ID_SUBTEMA_TOPICO = P.ID_SUBTEMA_TOPICO\r\n"
			+ "JOIN subtema S ON ST.ID_SUBTEMA = S.ID_SUBTEMA\r\n"
			+ "JOIN TEMA T ON T.ID_TEMA = S.ID_TEMA\r\n"
			+ "JOIN TOPICO TOP ON TOP.ID_TOPICO = ST.ID_TOPICO;";
}
