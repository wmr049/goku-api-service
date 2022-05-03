package com.goku.domain.address.service;

import com.goku.domain.address.dto.response.EstadoResponse;
import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.repository.filtro.EstadoFiltro;
import com.goku.infraestructure.config.exception.EstadoNaoEncontradoException;
import com.goku.infraestructure.config.exception.UnicidadeEstadoException;
import com.goku.infraestructure.config.exception.UnicidadePaisEstadoException;

import java.util.List;

public interface EstadoService {

    EstadoResponse salvar(Estado estado) throws UnicidadeEstadoException, UnicidadePaisEstadoException;

    EstadoResponse filtrarPorUF(String sigla) throws EstadoNaoEncontradoException;

    EstadoResponse filtrarPorId(long id) throws EstadoNaoEncontradoException;

    List<EstadoResponse> filtrar(EstadoFiltro filtro);
}
