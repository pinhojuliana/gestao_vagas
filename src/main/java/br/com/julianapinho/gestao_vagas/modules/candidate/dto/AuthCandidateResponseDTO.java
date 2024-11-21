package br.com.julianapinho.gestao_vagas.modules.candidate.dto;

import java.time.Instant;

public record AuthCandidateResponseDTO(String access_token, Instant expires_in) {
}
/*
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCandidateResponseDTO {
    private String acess_token;
}
 */
