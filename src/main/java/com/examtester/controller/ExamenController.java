package com.examtester.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examtester.business.Bi;
import com.examtester.entidad.PreguntaInfoVo;
import com.examtester.entidad.GenericResponse;
import com.examtester.entidad.Pregunta;

@CrossOrigin (value = "http://localhost:3000")
@RestController
@RequestMapping("/api/examen")
public class ExamenController {

    private final Bi bi;

    public ExamenController(Bi bi) {
        this.bi = bi;
    }
    
    public static final Logger log = LoggerFactory.getLogger(ExamenController.class);
    //http://localhost:8081/api/examen/temas
    @GetMapping("/temas")
    public Map<Integer,String> getTemas() {
        return bi.getTemas();
    }
  //http://localhost:8081/api/examen/subtemas/{idTema}
    @GetMapping("/subtemas/{idTema}")
    public Map<Integer,String> getSubtemas(@PathVariable("idTema")Long idTema) {
        return bi.getSubtemas(idTema);
    }
    
    @GetMapping("/topico/{idSubtema}")
    public Map<Integer,String> getTopicos(@PathVariable("idSubtema")Long idTopico) {
        return bi.getTopicos(idTopico);
    }
    
    @GetMapping("/pregunta/subtemaTopico/{idSubtemaTopico}")
    public PreguntaInfoVo getPreguntaXSubtemaTopico(@PathVariable("idSubtemaTopico")Long idSubtemaTopico) {
        return bi.getPreguntaXSubtemaTopico(idSubtemaTopico);
    }
    
    
    @GetMapping("/preguntas/allpreguntas")
    public List<PreguntaInfoVo> getPreguntas() {
        return bi.getAllPreguntas();
    }
    
    @GetMapping("/pregunta/{id}")
    public PreguntaInfoVo getPregunta(@PathVariable("id") Long id) {
        return bi.getPregunta(id);
    }
    

    @PostMapping(path = "insertar")
    public GenericResponse insertPregunta(@RequestBody Pregunta pregunta) {
    	return bi.insertarPregunta(pregunta);
    }
    

}