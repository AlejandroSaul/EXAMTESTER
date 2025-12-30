package com.examtester.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examtester.business.Bi;
import com.examtester.entidad.PreguntaInfoVo;

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
    
    /*Corregir mas adelante*/
//    @GetMapping("/topico/{idSubtemaTopico}")
//    public Map<Integer,String> getTopicos(@PathVariable("idSubtemaTopico")Long idTopico) {
//        return bi.getSubtemas(idTopico);
//    }
    
    
    @GetMapping("/preguntas/allpreguntas")
    public List<PreguntaInfoVo> getPreguntas() {
        return bi.getAllPreguntas();
    }
    
    @GetMapping("/pregunta/{id}")
    public PreguntaInfoVo getPregunta(@PathVariable("id") Long id) {
        return bi.getPregunta(id);
    }
    

    

}