package br.com.example.proposal.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "autenticacaoHeader";

        return new OpenAPI()
                .info(new Info()
                        .title("Proposal API")
                        .version("1.0.0")
                        .description("API para gerenciamento de propostas"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name("autenticacao")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .description("""
                                                Envie o JSON de autenticação no header, por exemplo:
                                                {"loginUsuario": "usuario", "senha": "123456"}
                                                """)));
    }
}
