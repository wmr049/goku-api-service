package com.goku.domain.address.repository;


import com.goku.domain.address.entity.Pais;
import com.goku.domain.address.repository.filtro.PaisFiltro;
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
public class PaisRepositoryTest {

    @Autowired
    private PaisRepository paisRepository;


    @Test
    public void deve_procurar_pais_por_EstadoSigla() throws Exception {

        Optional<Pais> optional = paisRepository.findByEstadoSigla("SP");

        assertThat(optional.isPresent()).isTrue();

        Pais paisTeste = optional.get();

        assertThat(paisTeste.getId()).isEqualTo(1L);
        assertThat(paisTeste.getNome()).isEqualTo("Brasil");
    }

    @Test
    public void deve_procurar_pais_por_estadosigla_e_estadotiporegiao() throws Exception {

        Optional<Pais> optional = paisRepository.findByEstadosSiglaAndEstadosTipoRegiao("SP", "SUDESTE");

        assertThat(optional.isPresent()).isTrue();

        Pais paisTeste = optional.get();

        assertThat(paisTeste.getId()).isEqualTo(1L);
        assertThat(paisTeste.getNome()).isEqualTo("Brasil");
    }

    @Test
    public void nao_deve_encontrar_pais_por_estadosigla_e_estadotiporegiao_nao_cadastrado() throws Exception {

        Optional<Pais> optional = paisRepository.findByEstadosSiglaAndEstadosTipoRegiao("RS", "SUL");

        assertThat(optional.isPresent()).isFalse();

    }

    @Test
    public void deve_filtrar_pais_por_parte_do_nome() throws Exception{

        PaisFiltro filtro = new PaisFiltro();
        filtro.setNome("Ch");

        List<Pais> paises = paisRepository.filtrar(filtro);
        assertThat(paises.size()).isEqualTo(0);
    }

    @Test
    public void deve_filtrar_pais_por_iso() throws Exception{
        PaisFiltro filtro = new PaisFiltro();
        filtro.setIso("R");

        List<Pais> paises = paisRepository.filtrar(filtro);
        assertThat(paises.size()).isEqualTo(3);
    }


    @Test
    public void deve_filtrar_pais_por_ddi() throws Exception{
        PaisFiltro filtro = new PaisFiltro();
        filtro.setDdi("00");

        List<Pais> paises = paisRepository.filtrar(filtro);
        assertThat(paises.size()).isEqualTo(2);
    }

    @Test
    public void deve_filtrar_pais_por_filtro_composto() throws Exception {
        PaisFiltro filtro = new PaisFiltro();
        filtro.setDdi("00");
        filtro.setNome("Ch");

        List<Pais> paises = paisRepository.filtrar(filtro);
        assertThat(paises.size()).isEqualTo(0);
    }

    @Test
    public void deve_filtrar_pais_por_tipo_regiao() throws Exception {
        PaisFiltro filtro = new PaisFiltro();
        filtro.setTipoRegiao("SUDESTE");

        List<Pais> paises = paisRepository.filtrar(filtro);
        assertThat(paises.size()).isEqualTo(2);
    }

}
