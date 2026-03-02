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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.examtester.business.Bi;
import com.examtester.entidad.PreguntaInfoVo;
import com.examtester.entidad.GenericResponse;
import com.examtester.entidad.Pregunta;

@CrossOrigin (origins = "${app.cors.allowed-origins}")
@RestController
@RequestMapping("/api/examen")
public class ExamenController {

    private final Bi bi;

    public ExamenController(Bi bi) {
        this.bi = bi;
    }
    
    public static final Logger log = LoggerFactory.getLogger(ExamenController.class);
    
    /**
     * Método que obtiene todos los temas existentes en BD
     * URL = http://localhost:8081/api/examen/temas
     * @return Mapa con el id y el nombre del tema del cual se desea obtener la información
     * @author Alejandro Saúl Baños
     * */
    @GetMapping("/temas")
    public Map<Integer,String> getTemas() {
        return bi.getTemas();
    }
    
    /**
     * Método que obtiene todos los subtemas existentes dentro de un tema en BD
     * URL = http://localhost:8081/api/examen/subtemas/{idTema}
     * @param id del tema del cual se desea obtener la información
     * @return Mapa con el id y el nombre del subtema
     * @author Alejandro Saúl Baños
     * */
    @GetMapping("/subtemas/{idTema}")
    public Map<Integer,String> getSubtemas(@PathVariable("idTema")Long idTema) {
        return bi.getSubtemas(idTema);
    }
    
    /**
     * Método que obtiene todos los topicos existentes dentro de un subtema en BD
     * URL = http://localhost:8081/api/examen/topico/{idSubtema}
     * @param id del idSubtema del cual se desea obtener la información
     * @return Mapa con el id y el nombre del subtema
     * @author Alejandro Saúl Baños
     * */
    @GetMapping("/topico/{idSubtema}")
    public Map<Integer,String> getTopicos(@PathVariable("idSubtema")Long idSubtema) {
        return bi.getTopicos(idSubtema);
    }
    
    /**
     * Método que obtiene una pregunta aleatoria de BD que tenga el idSubtemaTopico indicado
     * URL = http://localhost:8081/api/examen/pregunta/subtemaTopico/{idSubtemaTopico}
     * @param id del idSubtemaTopico del cual se desea obtener la información
     * @return PreguntaInfoVo Objeto de valor con la información de la pregunta
     * @author Alejandro Saúl Baños
     * */
    @GetMapping("/pregunta/subtemaTopico/{idSubtemaTopico}")
    public PreguntaInfoVo getPreguntaXSubtemaTopico(@PathVariable("idSubtemaTopico")Long idSubtemaTopico) {
        return bi.getPreguntaXSubtemaTopico(idSubtemaTopico);
    }
    
    /**
     * Método que inserta en BD todas las preguntas registradas en el archivo excel
     * URL = http://localhost:8081/api/examen/importar-excel
     * @param file objeto MultipartFile el cual debe ser llenado previamente antes de la carga
     * @return GenericResponse Objeto con la respuesta generica para obtener información de la consulta
     * @author Alejandro Saúl Baños
     * */
    @PostMapping("/importar-excel")
    public GenericResponse importarExcel(@RequestParam("file") MultipartFile file) {
            return bi.procesarExcelPreguntas(file);
    }

}