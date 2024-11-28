package br.com.julianapinho.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateJobDTO(
        //conteudo / é obrigatorio ou nao
        @Schema(example = "Vaga para pessoa desenvolvedora júnior", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String description,
        @Schema(example = "GYMPass, Plano de Saúde", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String benefits,
        @NotBlank
        @Schema(example = "Júnior", requiredMode = Schema.RequiredMode.REQUIRED)
        String level) {
}
