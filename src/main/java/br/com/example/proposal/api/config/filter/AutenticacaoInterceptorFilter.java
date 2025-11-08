package br.com.example.proposal.api.config.filter;


import br.com.example.proposal.api.domain.request.AutenticacaoRequest;
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

        try {

            String autenticacao = request.getHeader("autenticacao");

            if (StringUtils.hasText(autenticacao)) {
                AutenticacaoRequest autenticacaoRequest = objectMapper.readValue(autenticacao, AutenticacaoRequest.class);

                autenticarUsuario.executar(autenticacaoRequest.getLogin(), autenticacaoRequest.getSenha());

                // Cria um Authentication no contexto do Spring Security
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                autenticacaoRequest.getLogin(),
                                autenticacaoRequest.getSenha(),
                                Collections.emptyList()
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            log.error("Falha ao ler header de autenticação: {}", e.getMessage());
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
