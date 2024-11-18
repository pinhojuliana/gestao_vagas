package br.com.julianapinho.gestao_vagas.modules.company.use_cases;

import br.com.julianapinho.gestao_vagas.exceptions.UserFoundException;
import br.com.julianapinho.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.julianapinho.gestao_vagas.modules.company.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity){
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                        .ifPresent( user -> {
                            throw new UserFoundException("Compania já existente");
                        });

        return this.companyRepository.save(companyEntity);
    }
}
