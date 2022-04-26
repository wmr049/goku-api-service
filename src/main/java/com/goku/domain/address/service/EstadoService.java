package com.goku.domain.address.service;

import com.goku.domain.address.dto.response.EstadoResponse;
import com.goku.domain.address.entity.Estado;

public interface EstadoService {

    EstadoResponse salvar(Estado estado) ;

    EstadoResponse filtrarPorUF(String sigla) ;

    EstadoResponse filtrarPorId(long id) ;
}
