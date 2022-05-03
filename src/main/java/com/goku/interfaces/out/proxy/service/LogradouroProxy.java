package com.goku.interfaces.out.proxy.service;

import com.goku.domain.address.entity.Logradouro;

import java.util.Optional;

public interface LogradouroProxy {
    Logradouro salvar(Logradouro logradouro);

    Optional<Logradouro> consultaCep(String cep);
}
