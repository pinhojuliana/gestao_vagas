package br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.modules.candidate.use_cases;

import br.com.julianapinho.gestao_vagas.exceptions.JobNotFoundException;
import br.com.julianapinho.gestao_vagas.exceptions.UserNotFoundException;
import br.com.julianapinho.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.julianapinho.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.julianapinho.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.julianapinho.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.ApplyJobCandidateUseCase;
import br.com.julianapinho.gestao_vagas.modules.company.entities.JobEntity;
import br.com.julianapinho.gestao_vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    void should_not_be_able_to_apply_job_with_candidate_not_found(){
        Exception e = assertThrows(UserNotFoundException.class, () -> {
            applyJobCandidateUseCase.execute(UUID.randomUUID(), any());
        });

        assertEquals("User not found",e.getMessage());
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    void should_not_be_able_to_apply_job_with_job_not_found(){
        var candidateId = UUID.randomUUID();
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));

        Exception e = assertThrows(JobNotFoundException.class, () -> {
            applyJobCandidateUseCase.execute(candidateId, any());
        });

        assertEquals("Job not found",e.getMessage());
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    void should_be_able_to_create_a_new_apply_job(){
        var candidateId = UUID.randomUUID();
        var jobId = UUID.randomUUID();

        var jobEntity = JobEntity.builder()
                .id(jobId)
                .build();

        var candidateEntity = CandidateEntity.builder()
                .id(candidateId)
                .build();

        when(candidateRepository.findById(candidateId))
                .thenReturn(Optional.of(candidateEntity));
        when(jobRepository.findById(jobId))
                .thenReturn(Optional.of(jobEntity));

        var expectedApplyJob = ApplyJobEntity.builder()
                .id(UUID.randomUUID())
                .candidate(candidateEntity)
                .job(jobEntity)
                .build();

        when(applyJobRepository.save(any(ApplyJobEntity.class)))
                .thenReturn(expectedApplyJob);

        var result = applyJobCandidateUseCase.execute(candidateId, jobId);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
        assertEquals(expectedApplyJob, result);
    }

}