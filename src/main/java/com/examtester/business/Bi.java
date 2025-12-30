package com.examtester.business;

import java.util.List;
import java.util.Map;

import com.examtester.entidad.Pregunta;
import com.examtester.entidad.PreguntaInfoVo;

public interface Bi {
	
	public Map<Integer,String> getTemas();
	
	public Map<Integer,String> getSubtemas(Long idTema);
	
	public String getQuestinamiento(Pregunta pregunta);
	
	public List<String> getRespuestasPosibles(Pregunta pregunta);
	
	public String getRespuesta(Pregunta pregunta);
	
	public Integer getId(Pregunta pregunta);
	
	public String getExplicacion(Pregunta pregunta);
	
	public List<PreguntaInfoVo> getAllPreguntas();
	
	public PreguntaInfoVo getPregunta(Long id);
	
	
}
