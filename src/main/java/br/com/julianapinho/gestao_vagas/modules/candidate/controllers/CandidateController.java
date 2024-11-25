package br.com.julianapinho.gestao_vagas.modules.candidate.controllers;

import br.com.julianapinho.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.julianapinho.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.CreateCandidateUseCase;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<CandidateEntity> create(@Valid @RequestBody CandidateEntity candidateEntity){
        var result = this.createCandidateUseCase.execute(candidateEntity);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/")
    public ResponseEntity<ProfileCandidateResponseDTO> get(HttpServletRequest request){
        var candidateId = request.getAttribute("candidate_id");
        var profile = profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
        return ResponseEntity.ok(profile);
    }
}
