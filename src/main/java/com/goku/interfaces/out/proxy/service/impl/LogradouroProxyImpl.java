package com.goku.interfaces.out.proxy.service.impl;

import com.goku.domain.address.entity.Logradouro;
import com.goku.domain.address.repository.LogradouroRepository;
import com.goku.interfaces.out.proxy.service.ConsultaCepProcessor;
import com.goku.interfaces.out.proxy.service.LogradouroProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class LogradouroProxyImpl implements LogradouroProxy {

    private final LogradouroRepository logradouroRepository;

    @Override
    public Logradouro salvar(Logradouro logradouro) {
        return logradouroRepository.save(logradouro);
    }

    @Override
    public Optional<Logradouro> consultaCep(String cep) {

        ConsultaCepProcessor ViaCepProcessor = new ViaCepProcessorImpl();
        ConsultaCepProcessor WideNetProcessor = new WideNetProcessorImpl();
        ConsultaCepProcessor RepublicaVirtualProcessor = new RepublicaVirtualProcessorImpl();
        ConsultaCepProcessor PostmonProcessor = new PostmonProcessorImpl();

        ViaCepProcessor.setNextProcessor(WideNetProcessor);
        WideNetProcessor.setNextProcessor(RepublicaVirtualProcessor);
        RepublicaVirtualProcessor.setNextProcessor(PostmonProcessor);

        return ViaCepProcessor.process(cep);
    }
}
