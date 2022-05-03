package com.goku.interfaces.out.proxy.service;

import com.goku.domain.address.entity.Logradouro;

import java.util.Optional;

public interface ConsultaCepProcessor {
    void setNextProcessor(ConsultaCepProcessor consultaCepProcessor);
    Optional<Logradouro> process(String cep);
    Optional<Logradouro> convertLogradouro(Object objeto);
}
