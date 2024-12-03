package br.com.julianapinho.gestao_vagas.modules.candidate.use_cases;

import br.com.julianapinho.gestao_vagas.exceptions.JobNotFoundException;
import br.com.julianapinho.gestao_vagas.exceptions.UserNotFoundException;
import br.com.julianapinho.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.julianapinho.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.julianapinho.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.julianapinho.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID candidateId, UUID jobId){
        var candidate = candidateRepository.findById(candidateId)
                .orElseThrow(UserNotFoundException::new);

        var job = jobRepository.findById(jobId)
                .orElseThrow(JobNotFoundException::new);

        var applyJob = ApplyJobEntity.builder()
                .candidate(candidate)
                .job(job)
                .build();

        applyJob = applyJobRepository.save(applyJob);

        return applyJob;
    }
}
