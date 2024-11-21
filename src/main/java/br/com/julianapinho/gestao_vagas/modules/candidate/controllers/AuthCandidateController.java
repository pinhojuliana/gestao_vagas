package br.com.julianapinho.gestao_vagas.modules.candidate.controllers;

import br.com.julianapinho.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.julianapinho.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    public ResponseEntity<AuthCandidateResponseDTO> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var token = authCandidateUseCase.execute(authCandidateRequestDTO);
        return ResponseEntity.ok(token);
    }
}
