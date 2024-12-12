package br.com.julianapinho.gestao_vagas.modules.candidate.use_cases;

import br.com.julianapinho.gestao_vagas.exceptions.UserNotFoundException;
import br.com.julianapinho.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.julianapinho.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository repository;

    public ProfileCandidateResponseDTO execute(UUID candidateId){
        var candidate = this.repository.findById(candidateId)
                .orElseThrow(UserNotFoundException::new);

        return new ProfileCandidateResponseDTO(candidate.getName(),
                candidate.getUsername(),
                candidate.getEmail(),
                candidate.getDescription(),
                candidate.getId());
    }
}
