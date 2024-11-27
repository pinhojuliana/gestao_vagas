package br.com.julianapinho.gestao_vagas.modules.company.controllers;

import br.com.julianapinho.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.julianapinho.gestao_vagas.modules.company.entities.JobEntity;
import br.com.julianapinho.gestao_vagas.modules.company.use_cases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {
    @Autowired
    private CreateJobUseCase useCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<JobEntity> create(@RequestBody CreateJobDTO createJobDTO, HttpServletRequest request){
        Object companyId = request.getAttribute("company_id");

        var jobEntity = JobEntity.builder()
                .benefits(createJobDTO.benefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDTO.description())
                .level(createJobDTO.level())
                .build();

        var result = this.useCase.execute(jobEntity);
        return ResponseEntity.ok(result);
    }
}
