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
import com.examtester.entidad.PreguntaInfoVo;

@Repository
public class ExamenDAOImpl implements ExamenDAO {

    private final DataSource dataSource;

    public ExamenDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	@Override
	public List<PreguntaInfoVo> getAllPreguntas() {
		String sql = QuerysTester.QUERY_GET_ALL_PREGUNTAS;
		PreguntaInfoVo preguntaInfoVo = new PreguntaInfoVo();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		List<PreguntaInfoVo> preguntasInfoVo = new ArrayList();
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(sql);
			if(ps != null) {
				rs = ps.executeQuery();
				while(rs.next()) {
					preguntaInfoVo.setIdPregunta(rs.getString(ConstantesTester.CONST_ID_PREGUNTA));
					preguntaInfoVo.setPregunta(rs.getString(ConstantesTester.CONST_PREGUNTA));
					preguntaInfoVo.setImagenPregunta(rs.getString(ConstantesTester.CONST_IMAGEN_P));					
					preguntaInfoVo.setRespuestaA(rs.getString(ConstantesTester.CONST_RESPUESTA_A));					
					preguntaInfoVo.setImagenRespuestaA(rs.getString(ConstantesTester.CONST_IMAGEN_A));					
					preguntaInfoVo.setRespuestaB(rs.getString(ConstantesTester.CONST_RESPUESTA_B));
					preguntaInfoVo.setImagenRespuestaA(rs.getString(ConstantesTester.CONST_IMAGEN_B));					
					preguntaInfoVo.setRespuestaC(rs.getString(ConstantesTester.CONST_RESPUESTA_C));
					preguntaInfoVo.setImagenRespuestaA(rs.getString(ConstantesTester.CONST_IMAGEN_C));					
					preguntaInfoVo.setRespuestaD(rs.getString(ConstantesTester.CONST_RESPUESTA_D));
					preguntaInfoVo.setImagenRespuestaA(rs.getString(ConstantesTester.CONST_IMAGEN_D));					
					preguntaInfoVo.setRespuestaCorrecta(rs.getString(ConstantesTester.CONST_RESPUESTA_CORRECTA));
					preguntaInfoVo.setOrigen(rs.getString(ConstantesTester.CONST_ORIGEN));
					preguntaInfoVo.setNombreSubtema(rs.getString(ConstantesTester.CONST_NOMBRE_SUBTEMA));
					preguntaInfoVo.setNombreTema(rs.getString(ConstantesTester.CONST_NOMBRE_TEMA));
					preguntaInfoVo.setNombreTopico(rs.getString(ConstantesTester.CONST_NOMBRE_TOPICO));
					preguntasInfoVo.add(preguntaInfoVo);
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
		return preguntasInfoVo;
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
	
	@Override
	public PreguntaInfoVo getPregunta(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = QuerysTester.QUERY_GET_PREGUNTA;
		PreguntaInfoVo preguntaInfoVo = new PreguntaInfoVo();
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();	
			if(ps != null) {
				while(rs.next()) {									
					preguntaInfoVo.setIdPregunta(rs.getString(ConstantesTester.CONST_ID_PREGUNTA));
					preguntaInfoVo.setPregunta(rs.getString(ConstantesTester.CONST_PREGUNTA));
					preguntaInfoVo.setImagenPregunta(rs.getString(ConstantesTester.CONST_IMAGEN_P));					
					preguntaInfoVo.setRespuestaA(rs.getString(ConstantesTester.CONST_RESPUESTA_A));					
					preguntaInfoVo.setImagenRespuestaA(rs.getString(ConstantesTester.CONST_IMAGEN_A));					
					preguntaInfoVo.setRespuestaB(rs.getString(ConstantesTester.CONST_RESPUESTA_B));
					preguntaInfoVo.setImagenRespuestaA(rs.getString(ConstantesTester.CONST_IMAGEN_B));					
					preguntaInfoVo.setRespuestaC(rs.getString(ConstantesTester.CONST_RESPUESTA_C));
					preguntaInfoVo.setImagenRespuestaA(rs.getString(ConstantesTester.CONST_IMAGEN_C));					
					preguntaInfoVo.setRespuestaD(rs.getString(ConstantesTester.CONST_RESPUESTA_D));
					preguntaInfoVo.setImagenRespuestaA(rs.getString(ConstantesTester.CONST_IMAGEN_D));					
					preguntaInfoVo.setRespuestaCorrecta(rs.getString(ConstantesTester.CONST_RESPUESTA_CORRECTA));
					preguntaInfoVo.setOrigen(rs.getString(ConstantesTester.CONST_ORIGEN));
					preguntaInfoVo.setNombreSubtema(rs.getString(ConstantesTester.CONST_NOMBRE_SUBTEMA));
					preguntaInfoVo.setNombreTema(rs.getString(ConstantesTester.CONST_NOMBRE_TEMA));
					preguntaInfoVo.setNombreTopico(rs.getString(ConstantesTester.CONST_NOMBRE_TOPICO));
					preguntaInfoVo.setExplicacion(rs.getString(ConstantesTester.CONST_EXPLICACION));
					preguntaInfoVo.setImagenExplicacion(rs.getString(ConstantesTester.CONST_IMAGEN_EXPLICACION));
				}
			}
		}catch(Exception e) {
			System.out.println("Error al consultar los temas: "+e);
		}
		return preguntaInfoVo;
	}


}