package br.com.julianapinho.gestao_vagas.modules.candidate.controllers;

import br.com.julianapinho.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.julianapinho.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.julianapinho.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.ApplyJobCandidateUseCase;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.CreateCandidateUseCase;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.ListAllJobsByFilterUseCase;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.ProfileCandidateUseCase;
import br.com.julianapinho.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastro do candidato",
            description = "Essa função é responsável por cadastrar um novo candidato")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Candidato",
                    content = @Content(schema = @Schema(implementation = CandidateEntity.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Usuário já existe"
            )
    })
    public ResponseEntity<CandidateEntity> create(@Valid @RequestBody CandidateEntity candidateEntity){
        var result = this.createCandidateUseCase.execute(candidateEntity);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Perfil do candidato",
            description = "Essa função é responsável por buscar as informações do perfil do candidato")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de Jobs",
                    content = @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User not found"
            )
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ProfileCandidateResponseDTO> get(HttpServletRequest request){
        var candidateId = request.getAttribute("candidate_id");
        var profile = profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/job")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Listagem de vagas disponiveis para o candidato", description = "Essa função é responsável por listar todas as vagas disponiveis baseando-se no filtro")
    @ApiResponse(responseCode = "200",
            description = "Lista de Jobs",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class))))
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<List<JobEntity>> findJobByFilter(@RequestParam String filter){
        var result = listAllJobsByFilterUseCase.execute(filter);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/job/apply")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Aplicação em vagas", description = "Essa função é responsável por realizar a inscrição de um candidato a uma vaga")
    @ApiResponse(responseCode = "201",
            description = "Entidade ApplyJob",
            content = @Content(schema = @Schema(implementation = ApplyJobEntity.class)))
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<ApplyJobEntity> applyJob(HttpServletRequest request, @RequestBody UUID jobId){
        var candidateId = request.getAttribute("candidate_id");
        var result = applyJobCandidateUseCase.execute(UUID.fromString(candidateId.toString()), jobId);
        return ResponseEntity.ok(result);
    }
}
