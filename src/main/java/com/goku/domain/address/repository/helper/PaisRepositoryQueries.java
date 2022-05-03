package com.goku.domain.address.repository.helper;

import com.goku.domain.address.entity.Pais;
import com.goku.domain.address.repository.filtro.PaisFiltro;

import java.util.List;

public interface PaisRepositoryQueries {

    List<Pais> filtrar(PaisFiltro filtro);

}
