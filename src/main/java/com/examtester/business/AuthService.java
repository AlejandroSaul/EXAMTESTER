package com.examtester.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.examtester.dao.UsuarioDAO;
import com.examtester.entidad.GenericResponse;
import com.examtester.entidad.Usuario;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class AuthService {

    private final UsuarioDAO usuarioDAO;

    @Value("${JWT_SECRET:defaultSecret}")
    private String jwtSecret;

    @Value("${JWT_EXPIRATION:86400000}")
    private long jwtExpiration;

    public AuthService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public GenericResponse registro(Usuario usuario) {
        GenericResponse response = new GenericResponse();
        if (usuarioDAO.findByCorreo(usuario.getCorreoElectronico()) != null) {
            response.setCodigo(1);
            response.setMensaje("El correo ya esta registrado");
            return response;
        }
        String hashed = BCrypt.withDefaults().hashToString(12, usuario.getPassword().toCharArray());
        usuario.setPassword(hashed);
        int result = usuarioDAO.insertar(usuario);
        if (result > 0) {
            response.setCodigo(0);
            response.setMensaje("Usuario registrado correctamente");
        } else {
            response.setCodigo(1);
            response.setMensaje("Error al registrar usuario");
        }
        return response;
    }

    public GenericResponse login(String correoElectronico, String password) {
        GenericResponse response = new GenericResponse();
        Usuario usuario = usuarioDAO.findByCorreo(correoElectronico);
        if (usuario == null) {
            response.setCodigo(1);
            response.setMensaje("Credenciales incorrectas");
            return response;
        }
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), usuario.getPassword());
        if (!result.verified) {
            response.setCodigo(1);
            response.setMensaje("Credenciales incorrectas");
            return response;
        }
        String token = Jwts.builder()
                .subject(usuario.getCorreoElectronico())
                .claim("nombre", usuario.getNombre())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
        response.setCodigo(0);
        response.setMensaje(token);
        return response;
    }
}
