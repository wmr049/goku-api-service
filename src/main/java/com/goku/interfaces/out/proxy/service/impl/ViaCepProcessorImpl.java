package com.goku.interfaces.out.proxy.service.impl;

import com.goku.domain.address.entity.Logradouro;
import com.goku.interfaces.out.api.viacep.ViaCepClientHystrix;
import com.goku.interfaces.out.proxy.service.ConsultaCepProcessor;
import com.goku.interfaces.out.proxy.service.dto.ViaCep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViaCepProcessorImpl implements ConsultaCepProcessor {

    private ConsultaCepProcessor consultaCepProcessor;

    @Override
    public void setNextProcessor(ConsultaCepProcessor consultaCepProcessor) {
        this.consultaCepProcessor = consultaCepProcessor;
    }

    @Override
    public Optional<Logradouro> process(String cep) {
        ResponseEntity<ViaCep> viacep = new ViaCepClientHystrix().getByCEP(cep);
        return viacep.getStatusCode() != HttpStatus.NOT_FOUND ? convertLogradouro(viacep.getBody()) : consultaCepProcessor.process(cep);
    }

    @Override
    public Optional<Logradouro> convertLogradouro(Object object) {

        ViaCep viaCep = (ViaCep) object;

        return Optional.of(Logradouro.builder()
                .localidade(viaCep.getLocalidade())
                .bairro(viaCep.getBairro())
                .cep(viaCep.getCep().replaceAll("[^a-zA-Z0-9 ]", ""))
                .nome(viaCep.getLogradouro())
                .nomeAbreviado(viaCep.getLogradouro())
                .complemento(viaCep.getComplemento())
                .ufe(viaCep.getUf())
                .build());
    }

}