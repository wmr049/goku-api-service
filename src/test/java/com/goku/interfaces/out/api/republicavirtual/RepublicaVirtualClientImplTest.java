package com.goku.interfaces.out.api.republicavirtual;

import com.goku.interfaces.out.proxy.service.dto.RepublicaVirtual;
import com.goku.utils.StringUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.goku.utils.Constants.CEP;
import static com.goku.utils.Constants.CEP_INEXISTENTE;
import static com.goku.utils.Constants.NOME_ABREVIADO;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class RepublicaVirtualClientImplTest {

    private RepublicaVirtualClient republicaVirtualClient;


    @Before
    public void setUp() throws Exception {
        republicaVirtualClient = new RepublicaVirtualClientImpl();
    }


    @Test
    public void deve_retornar_dados_logradouro_consulta_api_via_cep_por_cep() throws Exception {
        ResponseEntity<RepublicaVirtual> logradouro = republicaVirtualClient.getByCEP(CEP);

        assertThat(logradouro).isNotNull();
        assertThat(logradouro.getBody().getLogradouro()).isEqualTo(StringUtil.convertUtf8(NOME_ABREVIADO));

    }

    @Test
    public void deve_retornar_status_nao_encontrado_quando_nao_encontrar_logradouro_consulta_republica_virutal_por_cep() throws Exception {
        ResponseEntity<RepublicaVirtual> logradouro = republicaVirtualClient.getByCEP(CEP_INEXISTENTE);

        assertThat(logradouro.getStatusCodeValue()).isEqualTo(404);
    }

}