package br.com.julianapinho.gestao_vagas.modules.candidate.use_cases;

import br.com.julianapinho.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.julianapinho.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.julianapinho.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    @Autowired
    String secretKey;

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var candidate = repository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        var passwordMatches = encoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        var token = JWT.create()
                .withIssuer("javagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", List.of("CANDIDATE"))
                .withExpiresAt(Instant.ofEpochSecond(expiresIn.toEpochMilli()))
                .sign(algorithm);

        return new AuthCandidateResponseDTO(token, expiresIn);
    }
}
