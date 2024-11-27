package br.com.julianapinho.gestao_vagas.modules.company.dto;

import java.time.Instant;

public record AuthCompanyResponseDTO(String acess_token, Instant expires_in) {
}
