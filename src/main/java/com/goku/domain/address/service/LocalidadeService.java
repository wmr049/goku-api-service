package com.goku.domain.address.service;

import com.goku.domain.address.dto.response.LocalidadeResponse;
import com.goku.domain.address.entity.Localidade;
import com.goku.domain.address.repository.filtro.LocalidadeFiltro;
import com.goku.infraestructure.config.exception.LocalidadeNaoEncontradoException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LocalidadeService {
    List<LocalidadeResponse> filtrarPorEstado(Long id) throws LocalidadeNaoEncontradoException;

    Page<Localidade> filtrarPorEstadoPaginado(Long id, int page, int size) throws LocalidadeNaoEncontradoException;

    Localidade filtrarPorNomeEEstadoSigla(String nome, String sigla) throws LocalidadeNaoEncontradoException;

    List<Localidade> filtrar(LocalidadeFiltro filtro);
}
