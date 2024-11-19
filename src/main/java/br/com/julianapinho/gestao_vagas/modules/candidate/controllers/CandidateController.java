package br.com.julianapinho.gestao_vagas.modules.candidate.controllers;

import br.com.julianapinho.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<CandidateEntity> create(@Valid @RequestBody CandidateEntity candidateEntity){
           var result = this.createCandidateUseCase.execute(candidateEntity);
           return ResponseEntity.ok(result);
    }
}
