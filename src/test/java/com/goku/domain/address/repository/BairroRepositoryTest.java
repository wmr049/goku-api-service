package com.goku.domain.address.repository;


import com.goku.domain.address.entity.Bairro;
import com.goku.utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(value= "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value= "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.yml")
public class BairroRepositoryTest {

    @Autowired
    BairroRepository bairroRepository;

    @Test
    public void deve_procurar_bairro_por_nome() throws Exception {

        Optional<Bairro> optional = bairroRepository.findByNome(StringUtil.convertUtf8("João Aranha"));

        assertThat(optional.isPresent()).isTrue();

        Bairro bairro = optional.get();

        assertThat(bairro.getNome()).isEqualTo(StringUtil.convertUtf8("João Aranha"));
    }

    @Test
    public void nao_deve_encontrar_bairro_por_nome_inexistente() throws Exception {

        Optional<Bairro> optional = bairroRepository.findByNome("Bairro Teste");

        assertThat(optional.isPresent()).isFalse();
    }

}
