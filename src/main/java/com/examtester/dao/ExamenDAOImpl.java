package com.examtester.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.examtester.constantes.ConstantesTester;
import com.examtester.constantes.QuerysTester;
import com.examtester.entidad.Pregunta;

@Repository
public class ExamenDAOImpl implements ExamenDAO {

    private final DataSource dataSource;

    public ExamenDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	@Override
	public List<Pregunta> getAllPreguntas() {
		String sql = QuerysTester.QUERY_GET_ALL_PREGUNTAS;
		Pregunta pregunta = new Pregunta();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		List<Pregunta> preguntas = new ArrayList();
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(sql);
			if(ps != null) {
				rs = ps.executeQuery();
				while(rs.next()) {
					pregunta.setIdPregunta(rs.getInt(ConstantesTester.CONST_ID_PREGUNTA));
					pregunta.setIdSubtemaTopico(rs.getInt(ConstantesTester.CONST_ID_SUBTEMA_TOPICO));
					pregunta.setIdOrigen(rs.getInt(ConstantesTester.CONST_ID_ORIGEN));
					pregunta.setPregunta(rs.getString(ConstantesTester.CONST_PREGUNTA));
					pregunta.setImagen(rs.getString(ConstantesTester.CONST_IMAGEN));
					pregunta.setRutaArchivo(rs.getString(ConstantesTester.CONST_RUTA_ARCHIVO));
					pregunta.setRespuestaA(rs.getString(ConstantesTester.CONST_RESPUESTA_A));
					pregunta.setRespuestaB(rs.getString(ConstantesTester.CONST_RESPUESTA_B));
					pregunta.setRespuestaC(rs.getString(ConstantesTester.CONST_RESPUESTA_C));
					pregunta.setRespuestaD(rs.getString(ConstantesTester.CONST_RESPUESTA_D));
					pregunta.setRespuestaCorrecta(rs.getString(ConstantesTester.CONST_RESPUESTA_CORRECTA));
					pregunta.setExplicacion(rs.getString(ConstantesTester.CONST_EXPLICACION));
					preguntas.add(pregunta);
				}
			}
		} catch (Exception e) {
			System.out.println("Error al obtener el PreparedStatement" + e);
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar las conexiones"+e);
			}
		}
		return preguntas;
	}

	@Override
	public Map<Integer, String> getTemas() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = QuerysTester.QUERY_GET_TEMAS;
		Map<Integer,String> resultado = new HashMap<Integer, String>();
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();	
			if(ps != null) {
				while(rs.next()) {									
					int idTema = rs.getInt("ID_TEMA");
					String tema = rs.getString("NOMBRE_TEMA");
					resultado.put(idTema, tema);
				}
			}
		}catch(Exception e) {
			System.out.println("Error al consultar los temas: "+e);
		}
		return resultado;
	}


}