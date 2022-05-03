package com.goku.domain.address.service.impl;

import com.goku.domain.address.entity.Logradouro;
import com.goku.domain.address.repository.LogradouroRepository;
import com.goku.domain.address.service.LogradouroService;
import com.goku.domain.user.service.UsuarioService;
import com.goku.infraestructure.config.exception.LogradouroNaoEncontradoException;
import com.goku.interfaces.out.proxy.service.LogradouroProxy;
import com.goku.interfaces.out.proxy.service.impl.LogradouroProxyImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogradouroServiceImpl implements LogradouroService {

    private final LogradouroRepository logradouroRepository;
    private final UsuarioService usuarioService;

    @Override
    public Logradouro filtrarPorCep(String cep) throws LogradouroNaoEncontradoException {
        usuarioService.isAdmin();

        final Optional<Logradouro> optional = logradouroRepository.findByCep(cep);

        if(!optional.isPresent()) {
            LogradouroProxy logradouroProxy = new LogradouroProxyImpl(logradouroRepository);
            Optional<Logradouro> optionalLogradouro = logradouroProxy.consultaCep(cep);
            if(optionalLogradouro.isPresent()) {
                logradouroProxy.salvar(optionalLogradouro.get());
            }
            return optionalLogradouro.orElseThrow(() -> new LogradouroNaoEncontradoException("Não existe logradouro cadastrado para o CEP "+ cep ));
        }

        return optional.orElseThrow(() -> new LogradouroNaoEncontradoException("Não existe logradouro cadastrado para o CEP "+ cep ));
    }
}
