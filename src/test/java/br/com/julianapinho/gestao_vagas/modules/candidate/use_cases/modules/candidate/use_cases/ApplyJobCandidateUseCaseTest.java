package br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.modules.candidate.use_cases;

import br.com.julianapinho.gestao_vagas.exceptions.JobNotFoundException;
import br.com.julianapinho.gestao_vagas.exceptions.UserNotFoundException;
import br.com.julianapinho.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.julianapinho.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.ApplyJobCandidateUseCase;
import br.com.julianapinho.gestao_vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApplyJobCandidateUseCaseTest {

    @InjectMocks    //fazer injeção da classe
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock   //dependencia da classe que será injetada
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    //depois ver no claude se posso usar a abordagem do "CaseX" pra os nomes do testes
    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found(){
        Exception e = assertThrows(UserNotFoundException.class, () -> {
            applyJobCandidateUseCase.execute(UUID.randomUUID(), any());
        });

        assertEquals("User not found",e.getMessage());

        /* forma que a professora utiilizou
        try{
            applyJobCandidateUseCase.execute(null, null);
        }
        catch{
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
         */
    }

    //null ou any?
    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void should_not_be_able_to_apply_job_with_job_not_found(){
        var candidateId = UUID.randomUUID();
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));

        Exception e = assertThrows(JobNotFoundException.class, () -> {
            applyJobCandidateUseCase.execute(candidateId, any());
        });

        assertEquals("Job not found",e.getMessage());
    }

}