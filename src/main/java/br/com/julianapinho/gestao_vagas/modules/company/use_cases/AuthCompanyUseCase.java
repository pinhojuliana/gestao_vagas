package br.com.julianapinho.gestao_vagas.modules.company.use_cases;

import br.com.julianapinho.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.julianapinho.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@RequiredArgsConstructor
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private final String secretKey;

    private final CompanyRepository companyRepository;

    private final PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        boolean passwordMatches = this.passwordEncoder.matches(authCompanyDTO.password(), company.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create().withIssuer("javagas")
                .withSubject(company.getId().toString())
                .sign(algorithm);
    }

}
