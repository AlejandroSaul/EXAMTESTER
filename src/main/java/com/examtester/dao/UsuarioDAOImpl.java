package com.examtester.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.examtester.constantes.QuerysTester;
import com.examtester.entidad.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    private final DataSource dataSource;

    public UsuarioDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Usuario findByCorreo(String correoElectronico) {
        String sql = QuerysTester.QUERY_FIND_USUARIO_BY_CORREO;
        Usuario usuario = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correoElectronico);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuarios(rs.getInt("idUsuarios"));
                    usuario.setNombre(rs.getString("Nombre"));
                    usuario.setPassword(rs.getString("Password"));
                    usuario.setCorreoElectronico(rs.getString("Correo_electronico"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar usuario: " + e);
        }
        return usuario;
    }

    @Override
    public int insertar(Usuario usuario) {
        String sql = QuerysTester.QUERY_INSERT_USUARIO;
        int resultado = 0;
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getCorreoElectronico());
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar usuario: " + e);
        }
        return resultado;
    }
}
