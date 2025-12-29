package com.examtester.dao;

import java.util.List;
import java.util.Map;

import com.examtester.entidad.Pregunta;
import com.examtester.entidad.PreguntaInfoVo;

public interface ExamenDAO {
	Pregunta pregunta = new Pregunta();
	
	public List <PreguntaInfoVo> getAllPreguntas();

	public PreguntaInfoVo getPregunta(Long id);
	
	public Map<Integer, String> getTemas();
	
	public Map<Integer, String> getSubtemas(Long idTema);
}
