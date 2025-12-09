package br.com.example.proposal.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String mensagem = "Acesso não autorizado.";

        if (authException != null && authException.getMessage() != null) {
            if (authException.getMessage().contains("Full authentication")) {
                mensagem = "Autenticação não informada.";
            } else {
                mensagem = authException.getMessage();
            }
        }

        Map<String, Object> body = new HashMap<>();
        body.put("status", 401);
        body.put("mensagem", mensagem);

        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
