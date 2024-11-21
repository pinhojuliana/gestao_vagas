package br.com.julianapinho.gestao_vagas.modules.company.use_cases;

import br.com.julianapinho.gestao_vagas.modules.company.entities.JobEntity;
import br.com.julianapinho.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity){
        return this.jobRepository.save(jobEntity);
    }
}
