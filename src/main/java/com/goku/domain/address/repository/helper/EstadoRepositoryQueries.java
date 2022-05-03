package com.goku.domain.address.repository.helper;

import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.repository.filtro.EstadoFiltro;

import java.util.List;

public interface EstadoRepositoryQueries {
    List<Estado> filtrar(EstadoFiltro filtro);
}
