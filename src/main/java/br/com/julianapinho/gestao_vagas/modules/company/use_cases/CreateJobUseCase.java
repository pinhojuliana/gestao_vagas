package br.com.julianapinho.gestao_vagas.modules.company.use_cases;

import br.com.julianapinho.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.julianapinho.gestao_vagas.modules.company.entities.JobEntity;
import br.com.julianapinho.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.julianapinho.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity){

        /* recebo um dto e um id de company(Object) e crio o obj aqui
        var jobEntity = JobEntity.builder()
                .benefits(createJobDTO.benefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(createJobDTO.description())
                .level(createJobDTO.level())
                .build();
         */
        companyRepository.findById(jobEntity.getCompanyId())
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
        return this.jobRepository.save(jobEntity);
    }
}
