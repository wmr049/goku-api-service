package com.goku.interfaces.out.proxy.service.impl;

import com.goku.domain.address.entity.Logradouro;
import com.goku.interfaces.out.api.postmon.PostmonClientHystrix;
import com.goku.interfaces.out.proxy.service.ConsultaCepProcessor;
import com.goku.interfaces.out.proxy.service.dto.Postmon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class PostmonProcessorImpl implements ConsultaCepProcessor {

    private ConsultaCepProcessor consultaCepProcessor;

    @Override
    public void setNextProcessor(ConsultaCepProcessor consultaCepProcessor) {
        this.consultaCepProcessor = consultaCepProcessor;
    }

    @Override
    public Optional<Logradouro> process(String cep) {
        ResponseEntity<Postmon> postmon = new PostmonClientHystrix().getByCEP(cep);
        return postmon.getStatusCode() != HttpStatus.NOT_FOUND ? convertLogradouro(postmon.getBody()) : Optional.ofNullable(null);
    }

    @Override
    public Optional<Logradouro> convertLogradouro(Object objeto) {
        Postmon postmon = (Postmon) objeto;
        return Optional.of(Logradouro.builder()
                .localidade(postmon.getCidade())
                .bairro(postmon.getBairro())
                .cep(postmon.getCep().replaceAll("[^a-zA-Z0-9 ]", ""))
                .nome(postmon.getLogradouro())
                .nomeAbreviado(postmon.getLogradouro())
                .ufe(postmon.getEstado())
                .build());
    }
}
