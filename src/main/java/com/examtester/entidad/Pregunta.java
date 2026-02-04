package com.examtester.entidad;

import java.util.Objects;

public class Pregunta {
	private Integer idPregunta;
	private Integer idSubtemaTopico;
	private Integer idOrigen;
	private String pregunta;
	private String imagen;
	private String rutaArchivo;
	private String respuestaA;
	private String respuestaB;
	private String respuestaC;
	private String respuestaD;
	private String respuestaE;
	private String respuestaF;
	private String respuestaG;
	private String respuestaH;
	private String respuestaI;
	private String respuestaCorrecta;
	private Integer idMateria;
	private String explicacion;
	
	private String unidad;



	public Pregunta(Integer idPregunta, Integer idSubtemaTopico, Integer idOrigen, String pregunta, String imagen,
			String rutaArchivo, String respuestaA, String respuestaB, String respuestaC, String respuestaD,
			String respuestaE, String respuestaF, String respuestaG, String respuestaH, String respuestaI,
			String respuestaCorrecta, Integer idMateria, String explicacion, String unidad) {
		super();
		this.idPregunta = idPregunta;
		this.idSubtemaTopico = idSubtemaTopico;
		this.idOrigen = idOrigen;
		this.pregunta = pregunta;
		this.imagen = imagen;
		this.rutaArchivo = rutaArchivo;
		this.respuestaA = respuestaA;
		this.respuestaB = respuestaB;
		this.respuestaC = respuestaC;
		this.respuestaD = respuestaD;
		this.respuestaE = respuestaE;
		this.respuestaF = respuestaF;
		this.respuestaG = respuestaG;
		this.respuestaH = respuestaH;
		this.respuestaI = respuestaI;
		this.respuestaCorrecta = respuestaCorrecta;
		this.idMateria = idMateria;
		this.explicacion = explicacion;
		this.unidad = unidad;
	}

	public Pregunta() {
		super();
	}

	public Integer getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}

	public Integer getIdSubtemaTopico() {
		return idSubtemaTopico;
	}

	public void setIdSubtemaTopico(Integer idSubtemaTopico) {
		this.idSubtemaTopico = idSubtemaTopico;
	}

	public Integer getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(Integer idOrigen) {
		this.idOrigen = idOrigen;
	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getRutaArchivo() {
		return rutaArchivo;
	}

	public void setRutaArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
	}

	public String getRespuestaA() {
		return respuestaA;
	}

	public void setRespuestaA(String respuestaA) {
		this.respuestaA = respuestaA;
	}

	public String getRespuestaB() {
		return respuestaB;
	}

	public void setRespuestaB(String respuestaB) {
		this.respuestaB = respuestaB;
	}

	public String getRespuestaC() {
		return respuestaC;
	}

	public void setRespuestaC(String respuestaC) {
		this.respuestaC = respuestaC;
	}

	public String getRespuestaD() {
		return respuestaD;
	}

	public void setRespuestaD(String respuestaD) {
		this.respuestaD = respuestaD;
	}

	public String getRespuestaCorrecta() {
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(String respuestaCorrecta) {
		this.respuestaCorrecta = respuestaCorrecta;
	}

	public String getExplicacion() {
		return explicacion;
	}

	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}


	public String getRespuestaE() {
		return respuestaE;
	}

	public void setRespuestaE(String respuestaE) {
		this.respuestaE = respuestaE;
	}

	public String getRespuestaF() {
		return respuestaF;
	}

	public void setRespuestaF(String respuestaF) {
		this.respuestaF = respuestaF;
	}

	public String getRespuestaG() {
		return respuestaG;
	}

	public void setRespuestaG(String respuestaG) {
		this.respuestaG = respuestaG;
	}

	public String getRespuestaH() {
		return respuestaH;
	}

	public void setRespuestaH(String respuestaH) {
		this.respuestaH = respuestaH;
	}

	public String getRespuestaI() {
		return respuestaI;
	}

	public void setRespuestaI(String respuestaI) {
		this.respuestaI = respuestaI;
	}
	

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	
	public Integer getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}

	@Override
	public String toString() {
		return "Pregunta [idPregunta=" + idPregunta + ", idSubtemaTopico=" + idSubtemaTopico + ", idOrigen=" + idOrigen
				+ ", pregunta=" + pregunta + ", imagen=" + imagen + ", rutaArchivo=" + rutaArchivo + ", respuestaA="
				+ respuestaA + ", respuestaB=" + respuestaB + ", respuestaC=" + respuestaC + ", respuestaD="
				+ respuestaD + ", respuestaE=" + respuestaE + ", respuestaF=" + respuestaF + ", respuestaG="
				+ respuestaG + ", respuestaH=" + respuestaH + ", respuestaI=" + respuestaI + ", respuestaCorrecta="
				+ respuestaCorrecta + ", idMateria=" + idMateria + ", explicacion=" + explicacion + ", unidad=" + unidad
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(explicacion, idOrigen, idPregunta, idSubtemaTopico, imagen, pregunta, respuestaA,
				respuestaB, respuestaC, respuestaCorrecta, respuestaD, rutaArchivo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pregunta other = (Pregunta) obj;
		return Objects.equals(explicacion, other.explicacion) && Objects.equals(idOrigen, other.idOrigen)
				&& Objects.equals(idPregunta, other.idPregunta)
				&& Objects.equals(idSubtemaTopico, other.idSubtemaTopico) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(pregunta, other.pregunta) && Objects.equals(respuestaA, other.respuestaA)
				&& Objects.equals(respuestaB, other.respuestaB) && Objects.equals(respuestaC, other.respuestaC)
				&& Objects.equals(respuestaCorrecta, other.respuestaCorrecta)
				&& Objects.equals(respuestaD, other.respuestaD) && Objects.equals(rutaArchivo, other.rutaArchivo);
	}

}
