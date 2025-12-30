package com.examtester.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.examtester.dao.ExamenDAO;
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
	public String getQuestinamiento(Pregunta pregunta) {		
		return pregunta.getPregunta();
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
	public PreguntaInfoVo getPregunta(Long id) {
		PreguntaInfoVo pregunta = examenDAO.getPregunta(id); 
		return pregunta;
	}
	
	



}
