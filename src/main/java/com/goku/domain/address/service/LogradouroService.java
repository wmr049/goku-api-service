package com.goku.domain.address.service;

import com.goku.domain.address.entity.Logradouro;
import com.goku.infraestructure.config.exception.LogradouroNaoEncontradoException;

public interface LogradouroService {
    Logradouro filtrarPorCep(String cep) throws LogradouroNaoEncontradoException;
}
