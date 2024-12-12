package br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.modules.company.controllers;

import br.com.julianapinho.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.modules.utils.TestUtils;
import br.com.julianapinho.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.julianapinho.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.julianapinho.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.modules.utils.TestUtils.objectToJSON;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setUp(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void should_create_a_new_job() throws Exception {

        var company = companyRepository.saveAndFlush(CompanyEntity.builder()
                .description("COMPANY_DESCRIPTION")
                .email("email@company.com")
                .password("#Teste123#")
                .username("COMPANY_USERNAME")
                .name("COMPANY_NAME")
                .website("https://www.company.com")
                .build());

        CreateJobDTO createJobDTO = new CreateJobDTO("DESCRIPTION_TEST",
                "BENEFITS_TEST",
                "LEVEL_TEST");

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJSON(createJobDTO))
                        .header("Authorization", TestUtils.generateToken(company.getId(),
                                "JAVAGAS_@123#")))
                .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }

    @Test
    public void should_not_create_a_new_job_because_company_is_not_found() throws Exception {
        CreateJobDTO createJobDTO = new CreateJobDTO("DESCRIPTION_TEST",
                "BENEFITS_TEST",
                "LEVEL_TEST");

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJSON(createJobDTO))
                        .header("Authorization", TestUtils.generateToken(UUID.randomUUID(),
                                "JAVAGAS_@123#")))
                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                        .andExpect(result -> assertInstanceOf(CompanyNotFoundException.class, result.getResolvedException()));
    }

}
