package com.goku.domain.address.service.impl;

import com.goku.domain.address.dto.response.LocalidadeResponse;
import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.entity.Localidade;
import com.goku.domain.address.entity.Pais;
import com.goku.domain.address.entity.TipoLocalidade;
import com.goku.domain.address.repository.LocalidadeRepository;
import com.goku.infraestructure.config.exception.LocalidadeNaoEncontradoException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.goku.utils.Constants.DDI;
import static com.goku.utils.Constants.ESTADO_NOVO;
import static com.goku.utils.Constants.IBGE;
import static com.goku.utils.Constants.IBGEID;
import static com.goku.utils.Constants.ID;
import static com.goku.utils.Constants.MUNICIPIO_ID;
import static com.goku.utils.Constants.NACIONALIDADE;
import static com.goku.utils.Constants.NOME_CIDADE;
import static com.goku.utils.Constants.PAGE;
import static com.goku.utils.Constants.PAIS;
import static com.goku.utils.Constants.REFERENCE;
import static com.goku.utils.Constants.SIGLA;
import static com.goku.utils.Constants.SITUACAO_LOCALIDADE_ATIVA;
import static com.goku.utils.Constants.SIZE;
import static com.goku.utils.Constants.TIPO_LOCALIDADE_NOME_CODIGO;
import static com.goku.utils.Constants.TIPO_LOCALIDADE_NOME_NOME;
import static com.goku.utils.Constants.TIPO_REGIAO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class LocalidadeServiceTest {

    @Mock
    private LocalidadeRepository localidadeRepository;

    @InjectMocks
    private LocalidadeServiceImpl localidadeService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void deve_procurar_localidade_por_estadoId() throws Exception {

        Estado estado = mockEstado();
        when(localidadeRepository.findByEstadoId(estado.getId())).thenReturn(Optional.of(mockLocalidades()));

        List<LocalidadeResponse> localidadeTeste = localidadeService.filtrarPorEstado(estado.getId());

        verify(localidadeRepository).findByEstadoId(estado.getId());

        assertThat(localidadeTeste).isNotNull();
        assertThat(localidadeTeste.size()).isEqualTo(1);

    }

    @Test
    public void deve_procurar_localidade_por_estadoId_paginado() throws Exception {

        Estado estado = mockEstado();

        Pageable pageable = PageRequest.of(PAGE, SIZE, Sort.Direction.ASC, "nome");
        when(localidadeRepository.findByEstadoId(estado.getId(), pageable)).thenReturn(new PageImpl(mockLocalidades()));

        Page<Localidade> localidadeTeste = localidadeService.filtrarPorEstadoPaginado(estado.getId(), PAGE, SIZE);

        verify(localidadeRepository).findByEstadoId(estado.getId(), pageable);

        assertThat(localidadeTeste).isNotNull();
        assertThat(localidadeTeste.getTotalPages()).isEqualTo(1);
    }

    @Test
    public void deve_procurar_localidade_por_nome_estadoSigla() throws Exception {

        Localidade localidade = mockLocalidade();
        Estado estado = mockEstado();


        when(localidadeRepository.findByNomeAndEstadoSigla(localidade.getNome(), estado.getSigla())).thenReturn(Optional.of(localidade));

        Localidade localidadeTeste = localidadeService.filtrarPorNomeEEstadoSigla(localidade.getNome(), estado.getSigla());

        verify(localidadeRepository).findByNomeAndEstadoSigla(localidade.getNome(), estado.getSigla());

        assertThat(localidadeTeste).isNotNull();
        assertThat(localidadeTeste.getNome()).isEqualTo("Campinas");
    }


    @Test(expected = LocalidadeNaoEncontradoException.class)
    public void deve_retornar_excessao_quando_nao_encontrar_localidade_por_estado_id() throws Exception {
        Estado estado = mockEstado();
        List<LocalidadeResponse> localidadeTeste = localidadeService.filtrarPorEstado(estado.getId());
    }

    @Test
    public void deve_retornar_dados_da_localidade_dentro_da_excecao_localidade_nao_encontrado() throws Exception {
        Estado estado = mockEstado();

        expectedException.expect(LocalidadeNaoEncontradoException.class);
        expectedException.expectMessage("Não existe localidade cadastrado para o Estado " + ID);

        localidadeService.filtrarPorEstado(estado.getId());
    }


    @Test(expected = LocalidadeNaoEncontradoException.class)
    public void deve_retornar_excessao_quando_nao_encontrar_localidade_por_nome_estado_sigla() throws Exception {
        Localidade localidade = mockLocalidade();
        Estado estado = mockEstado();
        localidadeService.filtrarPorNomeEEstadoSigla(localidade.getNome(), estado.getSigla());
    }

    @Test
    public void deve_retornar_dados_da_localidade_na_busca_localidade_por_nome_estado_sigla_dentro_da_excecao_localidade_nao_encontrado() throws Exception {
        Localidade localidade = mockLocalidade();
        Estado estado = mockEstado();

        expectedException.expect(LocalidadeNaoEncontradoException.class);
        expectedException.expectMessage("Não existe localidade cadastrado para o Estado " + SIGLA);

        localidadeService.filtrarPorNomeEEstadoSigla(localidade.getNome(), estado.getSigla());
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
}
