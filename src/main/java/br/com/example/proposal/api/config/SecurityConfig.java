package br.com.example.proposal.api.config;


import br.com.example.proposal.api.config.filter.AutenticacaoInterceptorFilter;
import br.com.example.proposal.api.exception.CustomAccessDeniedHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final AutenticacaoInterceptorFilter autenticacaoInterceptorFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AutenticacaoInterceptorFilter filter) throws Exception {
        return http
                .cors(cors -> {
                }) // 👈 habilita o CorsConfig
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()
                        .requestMatchers("/api/login/**").permitAll()
                        .requestMatchers("/ws-propostas/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(autenticacaoInterceptorFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
