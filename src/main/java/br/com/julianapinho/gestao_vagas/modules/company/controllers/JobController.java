package br.com.julianapinho.gestao_vagas.modules.company.controllers;

import br.com.julianapinho.gestao_vagas.modules.company.entities.JobEntity;
import br.com.julianapinho.gestao_vagas.modules.company.use_cases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private CreateJobUseCase useCase;

    @PostMapping("/")
    public ResponseEntity<JobEntity> create(@Valid @RequestBody JobEntity jobEntity){
        var result = this.useCase.execute(jobEntity);
        return ResponseEntity.ok(result);
    }
}
