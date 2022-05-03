package com.goku.interfaces.out.api.postmon;

import com.goku.interfaces.out.proxy.service.dto.Postmon;
import com.goku.utils.StringUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.goku.utils.Constants.CEP;
import static com.goku.utils.Constants.CEP_INEXISTENTE;
import static com.goku.utils.Constants.RUA;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PostmonClientImplTest {


    private PostmonClient postmonClient;

    @Before
    public void setUp() throws Exception {
        postmonClient = new PostmonClientImpl();
    }

    @Test
    public void deve_retornar_dados_logradouro_consulta_api_postmon_por_cep() throws Exception {

        ResponseEntity<Postmon> logradouro = postmonClient.getByCEP(CEP);

        assertThat(logradouro).isNotNull();
        assertThat(logradouro.getBody().getLogradouro()).isEqualTo(StringUtil.convertUtf8(RUA));

    }

    @Test
    public void deve_retornar_status_nao_encontrado_quando_nao_encontrar_logradouro_consulta_postmon_por_cep() throws Exception {
        ResponseEntity<Postmon> logradouro = postmonClient.getByCEP(CEP_INEXISTENTE);

        assertThat(logradouro.getStatusCodeValue()).isEqualTo(404);
    }


}