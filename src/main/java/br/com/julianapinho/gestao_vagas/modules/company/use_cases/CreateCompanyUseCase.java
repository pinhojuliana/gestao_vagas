package br.com.julianapinho.gestao_vagas.modules.company.use_cases;

import br.com.julianapinho.gestao_vagas.exceptions.UserFoundException;
import br.com.julianapinho.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.julianapinho.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    CompanyRepository companyRepository;

    //é um bean na classe de config
    @Autowired
    PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity){
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                        .ifPresent( user -> {
                            throw new UserFoundException("Compania já existente");
                        });

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
