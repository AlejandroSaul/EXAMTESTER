package com.examtester.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter implements Filter {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        // Preflight CORS: no lleva Authorization, debe pasar sin validar.
        if ("OPTIONS".equalsIgnoreCase(httpReq.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        String header = httpReq.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            escribirError(httpRes, "Token no proporcionado");
            return;
        }

        String token = header.substring(7);
        try {
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .build()
                    .parseSignedClaims(token);
            httpReq.setAttribute("correoElectronico", claims.getPayload().getSubject());
            httpReq.setAttribute("nombre", claims.getPayload().get("nombre", String.class));
        } catch (Exception e) {
            System.out.println("Error validando JWT: " + e.getMessage());
            escribirError(httpRes, "Token invalido");
            return;
        }

        // IMPORTANTE: fuera del try de validación del token, para que los errores
        // del controller (p. ej. archivo Excel mal formado) no se reporten como 401.
        chain.doFilter(request, response);
    }

    private void escribirError(HttpServletResponse response, String mensaje) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"codigo\":1,\"mensaje\":\"" + mensaje + "\"}");
    }
}