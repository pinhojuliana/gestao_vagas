package br.com.julianapinho.gestao_vagas.modules.candidate.controllers;

import br.com.julianapinho.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.julianapinho.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.CreateCandidateUseCase;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.ListAllJobsByFilterUseCase;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.ProfileCandidateUseCase;
import br.com.julianapinho.gestao_vagas.modules.company.entities.JobEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @PostMapping("/")
    public ResponseEntity<CandidateEntity> create(@Valid @RequestBody CandidateEntity candidateEntity){
        var result = this.createCandidateUseCase.execute(candidateEntity);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<ProfileCandidateResponseDTO> get(HttpServletRequest request){
        var candidateId = request.getAttribute("candidate_id");
        var profile = profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<List<JobEntity>> findJobByFilter(@RequestParam String filter){
        var result = listAllJobsByFilterUseCase.execute(filter);
        return ResponseEntity.ok(result);
    }
}
