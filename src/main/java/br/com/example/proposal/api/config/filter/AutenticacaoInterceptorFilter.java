package br.com.example.proposal.api.config.filter;

import br.com.example.proposal.api.domain.request.AutenticacaoRequest;
import br.com.example.proposal.api.exception.UsuarioNaoAutenticadoException;
import br.com.example.proposal.api.usecase.AutenticarUsuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
@Slf4j
public class AutenticacaoInterceptorFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final AutenticarUsuario autenticarUsuario;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("autenticacao");

        if (!StringUtils.hasText(header)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            AutenticacaoRequest auth = objectMapper.readValue(header, AutenticacaoRequest.class);
            autenticarUsuario.executar(auth.getLogin(), auth.getSenha());
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            auth.getLogin(),
                            auth.getSenha(),
                            Collections.emptyList()
                    );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            log.error("Header de autenticação inválido", e);
            writeError(response, HttpServletResponse.SC_BAD_REQUEST,
                    "Header de autenticação está em formato inválido.");

        } catch (UsuarioNaoAutenticadoException e) {
            log.error("Falha de autenticação: {}", e.getMessage());
            writeError(response, HttpServletResponse.SC_UNAUTHORIZED,
                    "Usuário ou senha inválidos.");

        } catch (Exception e) {
            log.error("Erro inesperado no filtro de autenticação", e);
            writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Erro interno ao processar autenticação.");
        }
    }

    private void writeError(HttpServletResponse response, int status, String mensagem) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");

        Map<String, Object> body = new HashMap<>();
        body.put("status", status);
        body.put("mensagem", mensagem);

        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
