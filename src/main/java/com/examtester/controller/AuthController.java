package com.examtester.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examtester.business.AuthService;
import com.examtester.entidad.GenericResponse;
import com.examtester.entidad.LoginRequest;
import com.examtester.entidad.RegisterRequest;
import com.examtester.entidad.Usuario;

@CrossOrigin(origins = "${app.cors.allowed-origins}")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registro")
    public GenericResponse registro(@RequestBody RegisterRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreoElectronico(request.getCorreoElectronico());
        usuario.setPassword(request.getPassword());
        return authService.registro(usuario);
    }

    @PostMapping("/login")
    public GenericResponse login(@RequestBody LoginRequest request) {
        return authService.login(request.getCorreoElectronico(), request.getPassword());
    }
}
