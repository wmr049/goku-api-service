package com.goku.domain.address.repository.helper;

import com.goku.domain.address.entity.Localidade;
import com.goku.domain.address.repository.filtro.LocalidadeFiltro;

import java.util.List;

public interface LocalidadeRepositoryQueries {
    List<Localidade> filtrar(LocalidadeFiltro filtro);
}
