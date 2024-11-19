package br.com.julianapinho.gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    //o Security filter chain é um metodo que eu tenho no spring security, mas para que ele entendea que é um metodo que estou sobreescrevendo eu preciso definir que isso e um bean
    //bean = definir um objeto ja gerenciado pelo spring, algo como um override, ele sobrescreve
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //desabilitar o spring security. csrf é uma vulnerabilidade que existe em aplicações web, um tipo de ataque que o invasor engana o usuario para fazer ataques
        //csrf -> csrf.disable()
        http.csrf(AbstractHttpConfigurer::disable);
        //fazer o build do codigo
        return http.build();
    }
}
