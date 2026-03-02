package com.examtester.business;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.examtester.entidad.GenericResponse;
import com.examtester.entidad.PreguntaInfoVo;

public interface Bi {
	
    /**
     * Método que obtiene todos los temas existentes en BD
     * @return Mapa con el id y el nombre del tema del cual se desea obtener la información
     * @author Alejandro Saúl Baños
     * */
	public Map<Integer,String> getTemas();
	
    /**
     * Método que obtiene todos los subtemas existentes dentro de un tema en BD
     * @param id del tema del cual se desea obtener la información
     * @return Mapa con el id y el nombre del subtema
     * @author Alejandro Saúl Baños
     * */
	public Map<Integer,String> getSubtemas(Long idTema);
	
    /**
     * Método que obtiene todos los topicos existentes dentro de un subtema en BD
     * @param id del idSubtema del cual se desea obtener la información
     * @return Mapa con el id y el nombre del subtema
     * @author Alejandro Saúl Baños
     * */
	public Map<Integer,String> getTopicos(Long idSubtema);
	
    /**
     * Método que obtiene una pregunta aleatoria de BD que tenga el idSubtemaTopico indicado
     * @param id del idSubtemaTopico del cual se desea obtener la información
     * @return PreguntaInfoVo Objeto de valor con la información de la pregunta
     * @author Alejandro Saúl Baños
     * */
	public PreguntaInfoVo getPreguntaXSubtemaTopico(Long idSubtemaTopico);
	
    /**
     * Método que inserta en BD todas las preguntas registradas en el archivo excel
     * @param file objeto MultipartFile el cual debe ser llenado previamente antes de la carga
     * @return GenericResponse Objeto con la respuesta generica para obtener información del procesamientos
     * @author Alejandro Saúl Baños
     * */
	public GenericResponse procesarExcelPreguntas(MultipartFile file);
	
}
