package com.goku.domain.address.service.impl;

import com.goku.domain.address.dto.response.EstadoResponse;
import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.repository.EstadoRepository;
import com.goku.domain.address.service.EstadoService;

public class EstadoServiceImpl implements EstadoService {

    private EstadoRepository estadoRepository;

    public EstadoServiceImpl() {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public EstadoResponse salvar(Estado estado) {
        return null;
    }

    @Override
    public EstadoResponse filtrarPorUF(String sigla) {
        return null;
    }

    @Override
    public EstadoResponse filtrarPorId(long id) {
        return null;
    }
}
