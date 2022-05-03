package com.goku.domain.address.service.impl;

import com.goku.domain.address.dto.response.EstadoResponse;
import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.entity.Pais;
import com.goku.domain.address.repository.EstadoRepository;
import com.goku.infraestructure.config.exception.EstadoNaoEncontradoException;
import com.goku.infraestructure.config.exception.UnicidadeEstadoException;
import com.goku.infraestructure.config.exception.UnicidadePaisEstadoException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.goku.utils.Constants.DDI;
import static com.goku.utils.Constants.ESTADO_NOVO;
import static com.goku.utils.Constants.IBGEID;
import static com.goku.utils.Constants.ID;
import static com.goku.utils.Constants.NACIONALIDADE;
import static com.goku.utils.Constants.PAIS;
import static com.goku.utils.Constants.REFERENCE;
import static com.goku.utils.Constants.SIGLA;
import static com.goku.utils.Constants.TIPO_REGIAO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EstadoServiceImplTest {

    @Mock
    EstadoRepository estadoRepository;

    @InjectMocks
    EstadoServiceImpl estadoService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void deve_salvar_estado_no_repositorio() throws Exception {
        Estado estado = mockEstado();

        when(estadoRepository.save(estado)).thenReturn(estado);
        estadoService.salvar(estado);

        verify(estadoRepository).save(estado);
    }

    @Test(expected = UnicidadePaisEstadoException.class)
    public void nao_deve_salvar_dois_estado_iguais_no_mesmo_pais() throws Exception {
        Estado estado = mockEstado();

        when(estadoRepository.findByPaisAndSigla(estado.getPais(), estado.getSigla())).thenReturn(Optional.of(estado));

        estadoService.salvar(estado);
    }


    @Test
    public void nao_deve_salvar_dois_estados_com_a_mesma_sigla() throws Exception {
        Estado estado = mockEstado();

        when(estadoRepository.findBySiglaAndNome(SIGLA,ESTADO_NOVO)).thenReturn(Optional.of(estado));

        expectedException.expect(UnicidadeEstadoException.class);
        expectedException.expectMessage("Já existe um estado cadastrado com a sigla '"+ SIGLA +"'");

        estadoService.salvar(estado);
    }

    @Test
    public void deve_procurar_por_uf()  throws Exception{
        Estado estado = mockEstado();

        when(estadoRepository.findBySigla(SIGLA)).thenReturn(Optional.of(estado));
        when(estadoRepository.save(estado)).thenReturn(estado);

        EstadoResponse estadoTeste = estadoService.filtrarPorUF(SIGLA);

        verify(estadoRepository).findBySigla(SIGLA);

        assertThat(estadoTeste).isNotNull();
        assertThat(estadoTeste.getNome()).isEqualTo(ESTADO_NOVO);
        assertThat(estadoTeste.getSigla()).isEqualTo(SIGLA);

    }

    @Test
    public void deve_procurar_por_id()  throws Exception{
        Estado estado = mockEstado();

        when(estadoRepository.findById(ID)).thenReturn(Optional.of(estado));
        when(estadoRepository.save(estado)).thenReturn(estado);

        EstadoResponse estadoTeste = estadoService.filtrarPorId(ID);

        verify(estadoRepository).findById(ID);

        assertThat(estadoTeste).isNotNull();
        assertThat(estadoTeste.getNome()).isEqualTo(ESTADO_NOVO);
        assertThat(estadoTeste.getSigla()).isEqualTo(SIGLA);

    }

    @Test(expected = EstadoNaoEncontradoException.class)
    public void deve_retornar_excessao_quando_nao_encontrar_estado_por_uf() throws Exception{
        estadoService.filtrarPorUF(SIGLA);
    }

    @Test
    public void deve_retornar_dados_do_estado_dentro_da_excecao_estado_nao_encontrado() throws Exception {
        expectedException.expect(EstadoNaoEncontradoException.class);
        expectedException.expectMessage("Não existe estado cadastrado com esta sigla "+ SIGLA );

        estadoService.filtrarPorUF(SIGLA);
    }

    @Test(expected = EstadoNaoEncontradoException.class)
    public void deve_retornar_excessao_quando_nao_encontrar_estado_por_id() throws Exception{
        estadoService.filtrarPorId(ID);
    }

    private Pais mockPais() {
        return Pais.builder()
                .nome(PAIS)
                .ddi(DDI)
                .nacionalidade(NACIONALIDADE)
                .build();
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
}