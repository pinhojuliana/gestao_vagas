package br.com.julianapinho.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

public record ProfileCandidateResponseDTO(String name,
                                          String username,
                                          String email,
                                          String description,
                                          UUID id) {
}
