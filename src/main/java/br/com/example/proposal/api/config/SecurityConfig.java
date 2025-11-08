package br.com.example.proposal.api.config;


import br.com.example.proposal.api.config.filter.AutenticacaoInterceptorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AutenticacaoInterceptorFilter filter) throws Exception {
        return http
                .cors(cors -> {}) // 👈 habilita o CorsConfig
                .csrf(csrf -> csrf.disable())
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
                .addFilterBefore(filter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
