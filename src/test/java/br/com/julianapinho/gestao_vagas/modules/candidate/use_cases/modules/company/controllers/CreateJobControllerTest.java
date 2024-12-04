package br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.modules.company.controllers;

import br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.modules.utils.TestUtils;
import br.com.julianapinho.gestao_vagas.modules.company.dto.CreateJobDTO;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static br.com.julianapinho.gestao_vagas.modules.candidate.use_cases.modules.utils.TestUtils.objectToJSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //criar uma porta randomica para rodar a aplicação -> vai subir a aplicação e executar o teste
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    //estrutura para rodar os testes
    @Before
    public void setUp(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
        //faço um mock do spring security
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {
        /* adicionar dependencia do junit e do spring security test
        1- primeiro vejo o tipo de metodo (post)
        2- coloco o endpoint que queor testar
        3- passo o tipo de content que nesse caso e o json (formato qu espero receber no controller)
        4- conteudo (O que estou recebendo no body?) -> nesse caso o dto de criação de um job
        **se espero um json, preciso receber um json -> faço uma conversão
        5- ObjectMapper = dependencia que temos no java para converter objetos
        6- depois disso eu falo que espero que a requisição dê sucesso
        **performo algo e espero um resultado!

        CORRIGINDO ERRO DE AUTENTICAÇÃO
        preciso de um token -> para criar o token posso: 1- baseado no mvc chamar a rota de autenticação
        2- criar um token e pasa-lo dentro da requisição
        **a abordagem vai ser a 2 porque irá facilitar nos outros testes já que poderá ser reutilizado
         */
        CreateJobDTO createJobDTO = new CreateJobDTO("DESCRIPTION_TEST", "BENEFITS_TEST", "LEVEL_TEST");

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJSON(createJobDTO))
                        .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), "JAVAGAS_@123#")))
                .andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }


}
