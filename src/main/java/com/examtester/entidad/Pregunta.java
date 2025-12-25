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
	private String respuestaCorrecta;
	private String explicacion;

	public Pregunta(Integer idPregunta, Integer idSubtemaTopico, Integer idOrigen, String pregunta, String imagen,
			String rutaArchivo, String respuestaA, String respuestaB, String respuestaC, String respuestaD,
			String respuestaCorrecta, String explicacion) {
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
		this.respuestaCorrecta = respuestaCorrecta;
		this.explicacion = explicacion;
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

	@Override
	public String toString() {
		return "Preguntas [idPregunta=" + idPregunta + ", idSubtemaTopico=" + idSubtemaTopico + ", idOrigen=" + idOrigen
				+ ", pregunta=" + pregunta + ", imagen=" + imagen + ", rutaArchivo=" + rutaArchivo + ", respuestaA="
				+ respuestaA + ", respuestaB=" + respuestaB + ", respuestaC=" + respuestaC + ", respuestaD="
				+ respuestaD + ", respuestaCorrecta=" + respuestaCorrecta + ", explicacion=" + explicacion + "]";
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
