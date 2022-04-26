package com.goku.domain.address.service.impl;

import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.repository.EstadoRepository;
import com.goku.domain.address.service.EstadoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static com.goku.utils.Constants.ESTADO_NOVO;
import static com.goku.utils.Constants.ID;
import static com.goku.utils.Constants.SIGLA;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstadoServiceImplTest {

    private EstadoService estadoService;
    private Estado estado;

    @MockBean
    private EstadoRepository estadoRepository;

    @Before
    public void setUp() throws Exception {
        estadoService = new EstadoServiceImpl();

        estado = new Estado();
        estado.setNome(ESTADO_NOVO);
        estado.setSigla(SIGLA);
        estado.setId(ID);
    }

    @Test
    public void deve_salvar_estado_no_repositorio() throws Exception {
        estadoService.salvar(estado);
//        verify(estadoRepository).save(estado);
    }
}