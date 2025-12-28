package com.examtester.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examtester.business.Bi;
import com.examtester.entidad.PreguntaInfoVo;


@RestController
@RequestMapping("/api/examen")
public class ExamenController {

    private final Bi bi;

    public ExamenController(Bi bi) {
        this.bi = bi;
    }

    @GetMapping("/preguntas/allpreguntas")
    public List<PreguntaInfoVo> getPreguntas() {
        return bi.getAllPreguntas();
    }
    
    @GetMapping("/pregunta/{id}")
    public PreguntaInfoVo getPregunta(@PathVariable("id") Long id) {
        return bi.getPregunta(id);
    }
}