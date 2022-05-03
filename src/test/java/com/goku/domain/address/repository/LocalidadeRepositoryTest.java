package com.goku.domain.address.repository;

import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.entity.Localidade;
import com.goku.domain.address.repository.filtro.LocalidadeFiltro;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.yml")
public class LocalidadeRepositoryTest {

    private static final int SIZE = 10;

    private static final int PAGE = 1;

    @Autowired
    LocalidadeRepository localidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Test
    public void deve_procurar_localidade_por_estado() throws Exception {

        Estado estado = estadoRepository.findBySigla("SP").get();
        Optional<List<Localidade>> optional = localidadeRepository.findByEstadoId(estado.getId());

        assertThat(optional.isPresent()).isTrue();
        assertThat(optional.get().size()).isEqualTo(8);
    }


    @Test
    public void deve_procurar_localidade_por_nome_estadosigla() throws Exception {

        Optional<Localidade> optional = localidadeRepository.findByNomeAndEstadoSigla("Campinas", "SP");

        assertThat(optional.isPresent()).isTrue();

        Localidade localidade = optional.get();
        assertThat(localidade.getNome()).isEqualTo("Campinas");
        assertThat(localidade.getPopulacao()).isEqualTo(1039297);
        assertThat(localidade.getIbgeId()).isEqualTo("3509502");
    }

    @Test
    public void nao_deve_encontrar_localidade_por_estadoId_inexistente() throws Exception {

        Optional<List<Localidade>> optional = localidadeRepository.findByEstadoId(999999L);

        assertThat(optional.isPresent()).isFalse();
    }

    @Test
    public void deve_filtrar_localidade_por_parte_do_nome() throws Exception {

        LocalidadeFiltro filtro = new LocalidadeFiltro();
        filtro.setNome("Araras");

        List<Localidade> localidades = localidadeRepository.filtrar(filtro);
        assertThat(localidades.size()).isEqualTo(1);
    }

    @Test
    public void deve_procurar_todas_localidades_paginado() throws Exception {
        Pageable pageable = PageRequest.of(PAGE, SIZE, Sort.Direction.ASC, "nome");


        Page<Localidade> pageLocalidade = localidadeRepository.findAll(pageable);

        assertThat(pageLocalidade.getNumber()).isEqualTo(1);
        assertThat(pageLocalidade.getSize()).isEqualTo(10);

    }

    @Test
    public void deve_procurar_localidades_por_estadoid_paginado() throws Exception {

        Estado estado = estadoRepository.findBySigla("SP").get();

        Pageable pageable = PageRequest.of(PAGE, SIZE, Sort.Direction.ASC, "nome");

        Page<Localidade> pageLocalidade = localidadeRepository.findByEstadoId(estado.getId(), pageable);


        assertThat(pageLocalidade.getPageable().getPageNumber()).isEqualTo(1);
        assertThat(pageLocalidade.getTotalElements()).isEqualTo(8);

    }

}
