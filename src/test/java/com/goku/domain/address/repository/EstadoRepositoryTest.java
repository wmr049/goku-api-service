package com.goku.domain.address.repository;

import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.repository.filtro.EstadoFiltro;
import com.goku.utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(value= "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value= "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.yml")
public class EstadoRepositoryTest {

    @Autowired
    private EstadoRepository estadoRepository;


    @Test
    public void deve_procurar_estado_por_sigla_e_nome() throws Exception {
        Optional<Estado> optional = estadoRepository.findBySiglaAndNome("RJ", "Rio de Janeiro");

        assertThat(optional.isPresent()).isTrue();

        Estado estadoTeste = optional.get();

        assertThat(estadoTeste.getId()).isEqualTo(2L);
        assertThat(estadoTeste.getNome()).isEqualTo("Rio de Janeiro");
        assertThat(estadoTeste.getSigla()).isEqualTo("RJ");
    }

    @Test
    public void nao_deve_encontrar_estado_por_sigla_ou_nome_inexistente() throws Exception {
        Optional<Estado> optional = estadoRepository.findBySiglaAndNome("SGP", "São Paulo");

        assertThat(optional.isPresent()).isFalse();
    }

    @Test
    public void deve_filtrar_estado_por_parte_do_nome() throws Exception{

        EstadoFiltro filtro = new EstadoFiltro();
        filtro.setNome(StringUtil.convertUtf8("S�"));

        List<Estado> estados = estadoRepository.filtrar(filtro);
        assertThat(estados.size()).isEqualTo(1);
    }

    @Test
    public void deve_filtrar_estado_por_tiporegiao() throws Exception{

        EstadoFiltro filtro = new EstadoFiltro();
        filtro.setTipoRegiao("SUDESTE");

        List<Estado> estados = estadoRepository.filtrar(filtro);
        assertThat(estados.size()).isEqualTo(2);
    }

    @Test
    public void deve_filtrar_estado_por_sigla() throws Exception{

        EstadoFiltro filtro = new EstadoFiltro();
        filtro.setSigla("SP");

        List<Estado> estados = estadoRepository.filtrar(filtro);
        assertThat(estados.size()).isEqualTo(1);
    }

    @Test
    public void deve_filtrar_estado_por_ibgeid() throws Exception{

        EstadoFiltro filtro = new EstadoFiltro();
        filtro.setIbgeId("33");

        List<Estado> estados = estadoRepository.filtrar(filtro);
        assertThat(estados.size()).isEqualTo(1);
    }
}