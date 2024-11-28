package br.com.julianapinho.gestao_vagas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Gestão de Vagas",
				description = "API responsável pela gestão de vagas",
				version = "1.0"
		)/*,
		servers = {
				@Server(url = "/", description = "Default Server URL")
		}*/
)
@SecurityScheme(name = "jwt_auth",
		scheme = "bearer", //qual o tipo? (nesse caso é bearer)
		bearerFormat = "JWT", //especificação do bearer
		type = SecuritySchemeType.HTTP,
		in = SecuritySchemeIn.HEADER) //baseado na requisição http
public class GestaoVagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVagasApplication.class, args);
	}

}
