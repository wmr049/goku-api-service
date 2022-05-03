package com.goku.interfaces.out.proxy.service.impl;

import com.goku.domain.address.entity.Bairro;
import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.entity.Localidade;
import com.goku.domain.address.entity.Logradouro;
import com.goku.domain.address.entity.Pais;
import com.goku.domain.address.entity.TipoLocalidade;
import com.goku.domain.address.repository.LogradouroRepository;
import com.goku.utils.StringUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.goku.utils.Constants.BAIRRO_NOME;
import static com.goku.utils.Constants.BAIRRO_NOME_ABREV;
import static com.goku.utils.Constants.CEP;
import static com.goku.utils.Constants.CEP_INEXISTENTE;
import static com.goku.utils.Constants.COMPLEMENTO;
import static com.goku.utils.Constants.DDI;
import static com.goku.utils.Constants.ESTADO_NOVO;
import static com.goku.utils.Constants.IBGE;
import static com.goku.utils.Constants.IBGEID;
import static com.goku.utils.Constants.ID;
import static com.goku.utils.Constants.MUNICIPIO_ID;
import static com.goku.utils.Constants.NACIONALIDADE;
import static com.goku.utils.Constants.NOME_ABREVIADO;
import static com.goku.utils.Constants.NOME_CIDADE;
import static com.goku.utils.Constants.PAIS;
import static com.goku.utils.Constants.REFERENCE;
import static com.goku.utils.Constants.RUA;
import static com.goku.utils.Constants.SIGLA;
import static com.goku.utils.Constants.SITUACAO_LOCALIDADE_ATIVA;
import static com.goku.utils.Constants.TIPO_LOCALIDADE_NOME_CODIGO;
import static com.goku.utils.Constants.TIPO_LOCALIDADE_NOME_NOME;
import static com.goku.utils.Constants.TIPO_REGIAO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class LogradouroProxyImplTest {

    private Estado estado;
    private Localidade localidade;
    private Bairro bairro;
    private Logradouro logradouro;

    private List<Localidade> localidades;
    private TipoLocalidade tipoLocalidade;

    @Mock
    LogradouroRepository logradouroRepository;

    @InjectMocks
    LogradouroProxyImpl logradouroProxy;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        logradouroProxy = new LogradouroProxyImpl(logradouroRepository);

        localidade = mockLocalidade();
        estado = mockEstado();
        localidades = mockLocalidades();
        logradouro = mockLogradouro();
    }


    @Test
    public void deve_realizar_consulta_cep_proxy() throws Exception {
        Optional<Logradouro> optional = logradouroProxy.consultaCep(CEP);
        assertThat(optional.isPresent()).isTrue();

        Logradouro logradouro = optional.get();
        assertThat(logradouro.getNome()).isEqualTo(StringUtil.convertUtf8(RUA));
    }

    @Test
    public void deve_realizar_consulta_cep_inexistente_proxy() throws Exception {

        Optional<Logradouro> optional = logradouroProxy.consultaCep(CEP_INEXISTENTE);
        assertThat(optional).isEmpty();
    }

    @Test
    public void deve_salvar_logradouro_apos_consulta() throws Exception {
        Optional<Logradouro> optional = logradouroProxy.consultaCep(CEP);
        assertThat(optional.isPresent()).isTrue();

        Logradouro logradouro = optional.get();
        assertThat(logradouro.getNome()).isEqualTo(StringUtil.convertUtf8(RUA));

        logradouroProxy.salvar(logradouro);
        verify(logradouroRepository).save(logradouro);
    }


    private Estado mockEstado() {
        return Estado.builder()
                .id(ID)
                .sigla(SIGLA)
                .tipoRegiao(TIPO_REGIAO)
                .ibgeId(IBGEID)
                .nome(ESTADO_NOVO)
                .reference(REFERENCE)
                .pais(mockPais())
                .build();
    }

    private Pais mockPais() {
        return Pais.builder()
                .nome(PAIS)
                .ddi(DDI)
                .nacionalidade(NACIONALIDADE)
                .build();
    }

    private Localidade mockLocalidade() {
        TipoLocalidade tipoLocalidade = TipoLocalidade.builder()
                .codigo(TIPO_LOCALIDADE_NOME_CODIGO)
                .nome(TIPO_LOCALIDADE_NOME_NOME)
                .build();

        return Localidade.builder()
                .nome(NOME_CIDADE)
                .situacao(SITUACAO_LOCALIDADE_ATIVA)
                .municipioId(MUNICIPIO_ID)
                .ibgeId(IBGE)
                .naturalidade(NOME_CIDADE)
                .tipoLocalidade(tipoLocalidade)
                .build();
    }

    private List<Localidade> mockLocalidades() {
        List<Localidade> localidades = new ArrayList<>();
        localidades.add(mockLocalidade());
        return localidades;
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