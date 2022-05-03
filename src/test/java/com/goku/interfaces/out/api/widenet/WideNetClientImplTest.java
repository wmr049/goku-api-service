package com.goku.interfaces.out.api.widenet;


import com.goku.interfaces.out.proxy.service.dto.WideNet;
import com.goku.utils.StringUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.goku.utils.Constants.CEP;
import static com.goku.utils.Constants.CEP_INEXISTENTE;
import static com.goku.utils.Constants.LOGRADOURO;
import static com.goku.utils.Constants.RUA;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class WideNetClientImplTest {

    private WideNetClient widNetClient;

    @Before
    public void setUp() throws Exception {
        widNetClient = new WideNetClientImpl();
    }

    @Test
    public void deve_retornar_dados_logradouro_consulta_api_widnet_por_cep() throws Exception {

        ResponseEntity<WideNet> logradouro = widNetClient.getByCEP(CEP);

        assertThat(logradouro).isNotNull();
        assertThat(logradouro.getBody().getAddress()).isEqualTo(StringUtil.convertUtf8(RUA));
    }

    @Test
    public void deve_retornar_status_nao_encontrado_quando_nao_encontrar_logradouro_consulta_widnet_por_cep() throws Exception{

        ResponseEntity<WideNet> logradouro = widNetClient.getByCEP(CEP_INEXISTENTE);

        assertThat(logradouro.getStatusCode().value()).isEqualTo(404);
    }

}