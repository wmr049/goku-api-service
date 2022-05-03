package com.goku.domain.address.service.impl;

import com.goku.domain.address.entity.Bairro;
import com.goku.domain.address.entity.Logradouro;
import com.goku.domain.address.repository.LogradouroRepository;
import com.goku.infraestructure.config.exception.LogradouroNaoEncontradoException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.goku.utils.Constants.BAIRRO_NOME;
import static com.goku.utils.Constants.BAIRRO_NOME_ABREV;
import static com.goku.utils.Constants.CEP;
import static com.goku.utils.Constants.CEP_INEXISTENTE;
import static com.goku.utils.Constants.CEP_INEXISTENTE_BASE;
import static com.goku.utils.Constants.COMPLEMENTO;
import static com.goku.utils.Constants.NOME_ABREVIADO;
import static com.goku.utils.Constants.RUA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class LogradouroServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private LogradouroRepository logradouroRepository;

    @InjectMocks
    private LogradouroServiceImpl logradouroService;


    @Test
    public void deve_realizar_busca_logradouro_por_cep() throws Exception {

        Logradouro logradouro = mockLogradouro();

        when(logradouroRepository.findByCep(logradouro.getCep())).thenReturn(Optional.of(logradouro));

        Logradouro logradouroTeste = logradouroService.filtrarPorCep(logradouro.getCep());
        verify(logradouroRepository).findByCep(logradouro.getCep());

        assertThat(logradouroTeste).isNotNull();
        assertThat(logradouroTeste.getCep()).isEqualTo(CEP);
    }

    @Test
    public void deve_realizar_busca_logradouro_por_cep_nao_existente_na_base() throws Exception{

        Logradouro logradouroTeste = logradouroService.filtrarPorCep(CEP_INEXISTENTE_BASE);

        assertThat(logradouroTeste).isNotNull();
        assertThat(logradouroTeste.getCep()).isEqualTo(CEP_INEXISTENTE_BASE);
    }

    @Test(expected = LogradouroNaoEncontradoException.class)
    public void deve_retornar_excessao_quando_nao_encontrar_logradouro_por_cep() throws Exception{
        logradouroService.filtrarPorCep(CEP_INEXISTENTE);
    }

    @Test
    public void deve_retornar_dados_do_logradouro_dentro_da_excecao_logradouro_nao_encontrado() throws Exception {
        expectedException.expect(LogradouroNaoEncontradoException.class);
        expectedException.expectMessage("Não existe logradouro cadastrado para o CEP "+ CEP_INEXISTENTE );

        logradouroService.filtrarPorCep(CEP_INEXISTENTE);
    }

    private Logradouro mockLogradouro() {

        Bairro bairro = Bairro.builder()
                .nome(BAIRRO_NOME)
                .nomeAbreviado(BAIRRO_NOME_ABREV)
                .build();

        return Logradouro.builder()
                .bairroInicio(bairro)
                .cep(CEP)
                .complemento(COMPLEMENTO)
                .nomeAbreviado(NOME_ABREVIADO)
                .nome(RUA)
                .build();


    }

}
