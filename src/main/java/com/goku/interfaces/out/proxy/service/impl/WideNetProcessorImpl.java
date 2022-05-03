package com.goku.interfaces.out.proxy.service.impl;

import com.goku.domain.address.entity.Logradouro;
import com.goku.interfaces.out.api.widenet.WideNetClientHystrix;
import com.goku.interfaces.out.proxy.service.ConsultaCepProcessor;
import com.goku.interfaces.out.proxy.service.dto.WideNet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WideNetProcessorImpl  implements ConsultaCepProcessor {

    private ConsultaCepProcessor consultaCepProcessor;

    @Override
    public void setNextProcessor(ConsultaCepProcessor consultaCepProcessor) {
        this.consultaCepProcessor = consultaCepProcessor;
    }

    @Override
    public Optional<Logradouro> process(String cep) {
        ResponseEntity<WideNet> widNet = new WideNetClientHystrix().getByCEP(cep);
        return widNet.getStatusCode() != HttpStatus.NOT_FOUND ? convertLogradouro(widNet.getBody()) : consultaCepProcessor.process(cep);
    }

    @Override
    public Optional<Logradouro> convertLogradouro(Object objeto) {
        WideNet widNet = (WideNet) objeto;

        return Optional.of(Logradouro.builder()
                .localidade(widNet.getCity())
                .bairro(widNet.getDistrict())
                .cep(widNet.getCode().replaceAll("[^a-zA-Z0-9 ]", ""))
                .nome(widNet.getAddress())
                .nomeAbreviado(widNet.getAddress())
                .ufe(widNet.getState())
                .build());
    }

}