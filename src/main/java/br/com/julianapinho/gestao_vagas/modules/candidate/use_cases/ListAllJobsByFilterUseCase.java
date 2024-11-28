package br.com.julianapinho.gestao_vagas.modules.candidate.use_cases;

import br.com.julianapinho.gestao_vagas.modules.company.entities.JobEntity;
import br.com.julianapinho.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter){
        return jobRepository.findByDescriptionContains(filter);
    }
}
