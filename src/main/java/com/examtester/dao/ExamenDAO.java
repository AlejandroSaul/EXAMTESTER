package com.examtester.dao;

import java.util.List;
import java.util.Map;

import com.examtester.entidad.Pregunta;

public interface ExamenDAO {
	Pregunta pregunta = new Pregunta();
	
	public List <Pregunta> getAllPreguntas();
	
	public Map<Integer, String> getTemas();
	
}
