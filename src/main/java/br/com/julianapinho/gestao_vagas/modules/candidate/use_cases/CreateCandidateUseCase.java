package br.com.julianapinho.gestao_vagas.modules.candidate.use_cases;

import br.com.julianapinho.gestao_vagas.exceptions.UserFoundException;
import br.com.julianapinho.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.julianapinho.gestao_vagas.modules.candidate.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;

    private final PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity){
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent(user -> {throw new UserFoundException("Usuário já existente");
                });


        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
    }
}
