package br.com.julianapinho.gestao_vagas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean // sobrescrever uma implementação que já existe
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info().title("Gestão de Vagas").description("API responsável pela gestão de vagas").version("1.0"))
                .schemaRequirement("jwt_auth", createSecurityScheme()); // so atribui dentro das requisições que a gente atribuiu esse scheam

                /*atribuiria a autenticação para todas as rotas
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createSecurityScheme()));*/
    }

    private SecurityScheme createSecurityScheme(){
        return new SecurityScheme()
                .name("jwt_auth")
                .scheme("bearer")
                .bearerFormat("JWT")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER);
    }
}
