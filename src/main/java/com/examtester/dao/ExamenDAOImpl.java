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
import com.examtester.entidad.GenericResponse;
import com.examtester.entidad.Pregunta;
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
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar las conexiones"+e);
			}
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
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar las conexiones"+e);
			}
		}
		return resultado;
	}
	
	@Override
	public Map<Integer, String> getTopicos(Long idSubtema){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = QuerysTester.QUERY_GET_TOPICOS;
		Map<Integer,String> resultado = new HashMap<Integer, String>();
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, idSubtema);
			rs = ps.executeQuery();	
			if(ps != null) {
				while(rs.next()) {									
					Integer idSubtemaTopico = rs.getInt("ID_SUBTEMA_TOPICO");
					String topico = rs.getString("NOMBRE_TOPICO");
					resultado.put(idSubtemaTopico, topico);
				}
			}
		}catch(Exception e) {
			System.out.println("Error al consultar los subtemas: "+e);
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar las conexiones"+e);
			}
		}
		return resultado;
	}
	
	@Override
	public ArrayList<Integer> getPreguntasXSubtemaTopico(Long idSubtemaTopico) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		String sql = QuerysTester.QUERY_GET_PREGUNTAS_SUBTEMATOPICO;
		ArrayList<Integer> arregloPreguntas = new ArrayList<Integer>();
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(sql);
			ps.setLong(1, idSubtemaTopico);
			rs = ps.executeQuery();	
			if(ps != null) {
				while(rs.next()) {									
					Integer idPregunta = rs.getInt("ID_PREGUNTA");
					arregloPreguntas.add(idPregunta);
				}
			}
		}catch(Exception e) {
			System.out.println("Error al consultar las preguntas: "+e);
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar las conexiones"+e);
			}
		}
		return arregloPreguntas;
	}
	
	


	
	@SuppressWarnings("unused")
	@Override
	public PreguntaInfoVo getPregunta(Long id) {

	    String sql = QuerysTester.QUERY_GET_PREGUNTA;
	    PreguntaInfoVo preguntaInfoVo = new PreguntaInfoVo();

	    try (Connection con = dataSource.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setLong(1, id);

	        try (ResultSet rs = ps.executeQuery()) {

	            if (rs.next()) {
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
	        }

	        asiganVacios(preguntaInfoVo);

	    } catch (Exception e) {
	        System.err.println("Error al consultar pregunta: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return preguntaInfoVo;
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
	public GenericResponse insertarPregunta(Pregunta pregunta) {

	    GenericResponse response = new GenericResponse();
	    
        	StringBuilder sql = new StringBuilder();
        	sql.append("INSERT INTO PREGUNTAS (");
        	sql.append("ID_PREGUNTA, ID_SUBTEMA_TOPICO, ID_ORIGEN, ");
        	sql.append("RESPUESTA_CORRECTA, PREGUNTA, RESPUESTA_A, RESPUESTA_B");

        	asignaQueryOpcionales(sql,pregunta);

        	sql.append(") VALUES (?,?,?,?,?,?,?");

        	if (pregunta.getRespuestaC() != null) sql.append(",?");
        	if (pregunta.getRespuestaD() != null) sql.append(",?");
        	if (pregunta.getRespuestaE() != null) sql.append(",?");
        	if (pregunta.getRespuestaF() != null) sql.append(",?");
        	if (pregunta.getRespuestaG() != null) sql.append(",?");
        	if (pregunta.getRespuestaH() != null) sql.append(",?");
        	if (pregunta.getRespuestaI() != null) sql.append(",?");
        	if (pregunta.getIdMateria() != null) sql.append(",?");
        	if (pregunta.getUnidad() != null) sql.append(",?");

        	sql.append(")");

        	try (Connection con = dataSource.getConnection();
        			PreparedStatement ps = con.prepareStatement(sql.toString())) {

        		Integer i = 1;

        		ps.setInt(i++, pregunta.getIdPregunta());
        		ps.setInt(i++, pregunta.getIdSubtemaTopico());
        		ps.setInt(i++, pregunta.getIdOrigen());
        		ps.setString(i++, pregunta.getRespuestaCorrecta());
        		ps.setString(i++, pregunta.getPregunta());
        		ps.setString(i++, asignarLetraInicial(pregunta.getRespuestaA(),"A"));
        		ps.setString(i++, asignarLetraInicial(pregunta.getRespuestaB(),"B"));

        		i = asignaQueryValoresOpcionales(ps, i, pregunta);

        		ps.executeUpdate();

        		response.setCodigo(0);
        		response.setMensaje("Pregunta insertada correctamente");

        	} catch (Exception e) {
        		e.printStackTrace();
        		response.setCodigo(1);
        		response.setMensaje("Error al insertar pregunta");
        	}
	    return response;
	}


	
	private void asignaQueryOpcionales(StringBuilder sql, Pregunta pregunta) {
	    if (pregunta.getRespuestaC() != null) sql.append(", RESPUESTA_C");
	    if (pregunta.getRespuestaD() != null) sql.append(", RESPUESTA_D");
	    if (pregunta.getRespuestaE() != null) sql.append(", RESPUESTA_E");
	    if (pregunta.getRespuestaF() != null) sql.append(", RESPUESTA_F");
	    if (pregunta.getRespuestaG() != null) sql.append(", RESPUESTA_G");
	    if (pregunta.getRespuestaH() != null) sql.append(", RESPUESTA_H");
	    if (pregunta.getRespuestaI() != null) sql.append(", RESPUESTA_I");
	    if (pregunta.getIdMateria() != null) sql.append(", ID_MATERIA");
	    if (pregunta.getUnidad() != null) sql.append(", UNIDAD");
	}

	private int asignaQueryValoresOpcionales(PreparedStatement ps, Integer i, Pregunta pregunta) throws Exception {
		try {
		       if (pregunta.getRespuestaC() != null) ps.setString(i++, asignarLetraInicial(pregunta.getRespuestaC(),"C"));
		        if (pregunta.getRespuestaD() != null) ps.setString(i++, asignarLetraInicial(pregunta.getRespuestaD(),"D") );
		        if (pregunta.getRespuestaE() != null) ps.setString(i++, asignarLetraInicial(pregunta.getRespuestaE(),"E") );
		        if (pregunta.getRespuestaF() != null) ps.setString(i++, asignarLetraInicial(pregunta.getRespuestaF(),"F"));
		        if (pregunta.getRespuestaG() != null) ps.setString(i++, asignarLetraInicial(pregunta.getRespuestaG(),"G"));
		        if (pregunta.getRespuestaH() != null) ps.setString(i++, asignarLetraInicial(pregunta.getRespuestaH(),"H"));
		        if (pregunta.getRespuestaI() != null) ps.setString(i++, asignarLetraInicial(pregunta.getRespuestaI(),"I"));
		        if (pregunta.getIdMateria() != null) ps.setInt(i++, pregunta.getIdMateria());
		        if (pregunta.getUnidad() != null) ps.setString(i++, pregunta.getUnidad());
		} catch (Exception e) {
	        e.printStackTrace();
	        throw e;
		}
		return i;
	}

	/**
	 * Método que asigna un guion de los datos vacios no obligatorias de una pregunta
	 * @param preguntaInfoVo value object que guarda la información traida de la entidad
	 * @author Alejandro Saul Baños Vega
	 * */
	private void asiganVacios(PreguntaInfoVo preguntaInfoVo) {
		asignarRespuestasVacias(preguntaInfoVo);
		
		asignarImagenesVacias(preguntaInfoVo);
		
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

	private void asignarImagenesVacias(PreguntaInfoVo preguntaInfoVo) {
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
	}

	private void asignarRespuestasVacias(PreguntaInfoVo preguntaInfoVo) {
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
	}

	private String asignarLetraInicial(String respuesta,String letra) {
		StringBuilder combinacionInicial = new StringBuilder();
		combinacionInicial.append(letra);
		combinacionInicial.append(".-");
		
		if(!respuesta.contains(combinacionInicial.toString())) {
			combinacionInicial.append(" ");
			combinacionInicial.append(respuesta);
			return combinacionInicial.toString();
		}
		return respuesta;
	}
}