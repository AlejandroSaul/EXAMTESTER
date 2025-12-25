package com.examtester.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examtester.business.Bi;
import com.examtester.entidad.Pregunta;


@RestController
@RequestMapping("/api/examen")
public class ExamenController {

    private final Bi bi;

    public ExamenController(Bi bi) {
        this.bi = bi;
    }

    @GetMapping("/preguntas")
    public List<Pregunta> getPreguntas() {
        return bi.getAllPreguntas();
    }
}