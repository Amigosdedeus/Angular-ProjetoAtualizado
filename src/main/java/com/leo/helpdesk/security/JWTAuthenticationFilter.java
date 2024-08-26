package com.leo.helpdesk.security;

// Imports Java
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

// Imports SLF4J para Logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Imports Spring Security
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Imports Jackson para JSON
import com.fasterxml.jackson.databind.ObjectMapper;

// Imports Jakarta Servlet API
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Imports do Projeto
import com.leo.helpdesk.domain.dtos.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        // Define a URL de login para /login
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            CredenciaisDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredenciaisDTO.class);
            logger.info("Tentando autenticar o usuário: {}", creds.getEmail());

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    creds.getEmail(), creds.getSenha(), new ArrayList<>());

            Authentication auth = authenticationManager.authenticate(authToken);
            logger.info("Autenticação bem-sucedida para o usuário: {}", creds.getEmail());
            return auth;
        } catch (IOException e) {
            logger.error("Erro ao tentar autenticar", e);
            throw new RuntimeException("Falha ao autenticar", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        String username = ((UserSS) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        logger.info("Token gerado para o usuário: {}", username);
        response.setHeader("Authorization", "Bearer " + token);
        response.setHeader("access-control-expose-headers", "Authorization");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            AuthenticationException failed) throws IOException {
        logger.warn("Autenticação falhou para o usuário: {}", request.getParameter("email"));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().append(json());
    }

    private String json() {
        long date = new Date().getTime();
        return "{"
                + "\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
    }
}


