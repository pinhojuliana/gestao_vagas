package br.com.julianapinho.gestao_vagas.modules.company.repositories;

import br.com.julianapinho.gestao_vagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    //SELECT * FROM JOB WHERE DESCRIPTION LIKE %FILTER%
    List<JobEntity> findByDescriptionContainsIgnoreCase(String description);
}
