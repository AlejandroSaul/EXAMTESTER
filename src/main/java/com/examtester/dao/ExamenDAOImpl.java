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
	public Map<Integer, String> getSubtemas(Long idTema) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = QuerysTester.QUERY_GET_SUBTEMAS_ID;
		Map<Integer,String> resultado = new HashMap<Integer, String>();
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, idTema);
			rs = ps.executeQuery();	
			if(ps != null) {
				while(rs.next()) {									
					Integer idSubTema = rs.getInt("ID_SUBTEMA");
					String subTema = rs.getString("NOMBRE_SUBTEMA");
					resultado.put(idSubTema, subTema);
				}
			}
		}catch(Exception e) {
			System.out.println("Error al consultar los subtemas: "+e);
		}
		return resultado;
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
	@SuppressWarnings("unused")
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
					preguntaInfoVo.setImagenRespuestaB(rs.getString(ConstantesTester.CONST_IMAGEN_B));					
					preguntaInfoVo.setRespuestaC(rs.getString(ConstantesTester.CONST_RESPUESTA_C));
					preguntaInfoVo.setImagenRespuestaC(rs.getString(ConstantesTester.CONST_IMAGEN_C));					
					preguntaInfoVo.setRespuestaD(rs.getString(ConstantesTester.CONST_RESPUESTA_D));
					preguntaInfoVo.setImagenRespuestaD(rs.getString(ConstantesTester.CONST_IMAGEN_D));					
					preguntaInfoVo.setRespuestaE(rs.getString(ConstantesTester.CONST_RESPUESTA_E));
					preguntaInfoVo.setImagenRespuestaE(rs.getString(ConstantesTester.CONST_IMAGEN_E));					
					preguntaInfoVo.setRespuestaF(rs.getString(ConstantesTester.CONST_RESPUESTA_F));
					preguntaInfoVo.setImagenRespuestaF(rs.getString(ConstantesTester.CONST_IMAGEN_F));					
					preguntaInfoVo.setRespuestaG(rs.getString(ConstantesTester.CONST_RESPUESTA_G));					
					preguntaInfoVo.setImagenRespuestaG(rs.getString(ConstantesTester.CONST_IMAGEN_G));					
					preguntaInfoVo.setRespuestaH(rs.getString(ConstantesTester.CONST_RESPUESTA_H));
					preguntaInfoVo.setImagenRespuestaH(rs.getString(ConstantesTester.CONST_IMAGEN_H));					
					preguntaInfoVo.setRespuestaI(rs.getString(ConstantesTester.CONST_RESPUESTA_I));
					preguntaInfoVo.setImagenRespuestaI(rs.getString(ConstantesTester.CONST_IMAGEN_I));					
					preguntaInfoVo.setRespuestaCorrecta(rs.getString(ConstantesTester.CONST_RESPUESTA_CORRECTA));
					preguntaInfoVo.setOrigen(rs.getString(ConstantesTester.CONST_ORIGEN));
					preguntaInfoVo.setNombreSubtema(rs.getString(ConstantesTester.CONST_NOMBRE_SUBTEMA));
					preguntaInfoVo.setNombreTema(rs.getString(ConstantesTester.CONST_NOMBRE_TEMA));
					preguntaInfoVo.setNombreTopico(rs.getString(ConstantesTester.CONST_NOMBRE_TOPICO));
					preguntaInfoVo.setExplicacion(rs.getString(ConstantesTester.CONST_EXPLICACION));
					preguntaInfoVo.setImagenExplicacion(rs.getString(ConstantesTester.CONST_IMAGEN_EXPLICACION));
				}
				asiganVacios(preguntaInfoVo);
			}
		}catch(Exception e) {
			System.out.println("Error al consultar los temas: "+e);
		}
		return preguntaInfoVo;
	}
	
	private void asiganVacios(PreguntaInfoVo preguntaInfoVo) {
		if(preguntaInfoVo.getRespuestaA() == null) {
			preguntaInfoVo.setRespuestaA(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getRespuestaB() == null) {
			preguntaInfoVo.setRespuestaB(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getRespuestaC() == null) {
			preguntaInfoVo.setRespuestaC(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getRespuestaC() == null) {
			preguntaInfoVo.setRespuestaC(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getRespuestaD() == null) {
			preguntaInfoVo.setRespuestaD(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getRespuestaE() == null) {
			preguntaInfoVo.setRespuestaE(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getRespuestaF() == null) {
			preguntaInfoVo.setRespuestaF(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getRespuestaG() == null) {
			preguntaInfoVo.setRespuestaG(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getRespuestaH() == null) {
			preguntaInfoVo.setRespuestaH(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getRespuestaI() == null) {
			preguntaInfoVo.setRespuestaI(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getImagenRespuestaA() == null) {
			preguntaInfoVo.setImagenRespuestaA(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getImagenRespuestaB() == null) {
			preguntaInfoVo.setImagenRespuestaB(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getImagenRespuestaC() == null) {
			preguntaInfoVo.setImagenRespuestaC(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getImagenRespuestaD() == null) {
			preguntaInfoVo.setImagenRespuestaD(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getImagenRespuestaE() == null) {
			preguntaInfoVo.setImagenRespuestaE(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getImagenRespuestaF() == null) {
			preguntaInfoVo.setImagenRespuestaF(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getImagenRespuestaG() == null) {
			preguntaInfoVo.setImagenRespuestaG(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getImagenRespuestaH() == null) {
			preguntaInfoVo.setImagenRespuestaH(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getImagenRespuestaI() == null) {
			preguntaInfoVo.setImagenRespuestaI(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getExplicacion() == null) {
			preguntaInfoVo.setExplicacion(ConstantesTester.CONST_GUION);
		}		
		if(preguntaInfoVo.getImagenExplicacion() == null) {
			preguntaInfoVo.setImagenExplicacion(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getNombreMateria() == null) {
			preguntaInfoVo.setNombreMateria(ConstantesTester.CONST_GUION);
		}
		if(preguntaInfoVo.getUnidad() == null) {
			preguntaInfoVo.setUnidad(ConstantesTester.CONST_GUION);
		}
	}


}