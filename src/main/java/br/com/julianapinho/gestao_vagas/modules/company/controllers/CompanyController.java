package br.com.julianapinho.gestao_vagas.modules.company.controllers;

import br.com.julianapinho.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.julianapinho.gestao_vagas.modules.company.use_cases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase useCase;

    @PostMapping("/")
    public ResponseEntity<CompanyEntity> create(@Valid @RequestBody CompanyEntity companyEntity){
        var result = this.useCase.execute(companyEntity);
        return ResponseEntity.ok(result);
    }
}
