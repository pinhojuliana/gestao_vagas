package br.com.julianapinho.gestao_vagas.modules.company.controllers;

import br.com.julianapinho.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.julianapinho.gestao_vagas.modules.company.use_cases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/company")
    public ResponseEntity<String> create(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var result = this.authCompanyUseCase.execute(authCompanyDTO);
        return ResponseEntity.ok(result);
    }
}
