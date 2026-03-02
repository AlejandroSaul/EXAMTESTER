package com.examtester.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.examtester.entidad.GenericResponse;
import com.examtester.entidad.Pregunta;
import com.examtester.entidad.PreguntaInfoVo;

public interface ExamenDAO {
	Pregunta pregunta = new Pregunta();
	
    /**
     * Método que obtiene todos los temas existentes en BD
     * @return Mapa con el id y el nombre del tema del cual se desea obtener la información
     * @author Alejandro Saúl Baños
     * */
	public Map<Integer, String> getTemas();
	
    /**
     * Método que obtiene todos los subtemas existentes dentro de un tema en BD
     * @param id del tema del cual se desea obtener la información
     * @return Mapa con el id y el nombre del subtema
     * @author Alejandro Saúl Baños
     * */
	public Map<Integer, String> getSubtemas(Long idTema);
	
    /**
     * Método que obtiene todos los topicos existentes dentro de un subtema en BD
     * @param id del idSubtema del cual se desea obtener la información
     * @return Mapa con el id y el nombre del subtema
     * @author Alejandro Saúl Baños
     * */
	public Map<Integer, String> getTopicos(Long idSubtema);
	
    /**
     * Método que obtiene una lista de ids de todas las preguntas que tengan el idSubtemaTopico indicado
     * @param id del idSubtemaTopico ligado a las preguntas
     * @return ArrayList<Integer> Lista de ids de preguntas petenecientes al idSubtemaTopico
     * @author Alejandro Saúl Baños
     * */
	public ArrayList<Integer> getPreguntasXSubtemaTopico(Long idSubtemaTopico);
	
    /**
     * Método que obtiene la pregunta solicitada mediante el id enviado 
     * @param id de la pregunta deseada
     * @return PreguntaInfoVo Objeto de valor con la información de la pregunta
     * @author Alejandro Saúl Baños
     * */
	public PreguntaInfoVo getPregunta(Long id);
	
    /**
     * Método que inserta una pregunta en la tabla Preguntas 
     * @param pregunta objeto de tipo Pregunta que contiene todos los datos a insertar
     * @return GenericResponse Objeto con la respuesta generica para obtener información de la inserción
     * @author Alejandro Saúl Baños
     * */
	public GenericResponse insertarPregunta(Pregunta pregunta);

}
