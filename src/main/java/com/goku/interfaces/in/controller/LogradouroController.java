package com.goku.interfaces.in.controller;

import com.goku.domain.address.entity.Logradouro;
import com.goku.domain.address.service.LogradouroService;
import com.goku.infraestructure.config.exception.LogradouroNaoEncontradoException;
import com.goku.interfaces.in.api.LogradouroApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/logradouro")
@RequiredArgsConstructor
@Slf4j
public class LogradouroController implements LogradouroApi {

    private final LogradouroService logradouroService;

    @Override
    public ResponseEntity<Logradouro> buscarPorCEP(String cep) throws LogradouroNaoEncontradoException {
        log.info("M=LocalidadeController.buscarPorCEP, cep={}", cep);
        return ResponseEntity.ok(logradouroService.filtrarPorCep(cep));
    }

    @ExceptionHandler({LogradouroNaoEncontradoException.class})
    public ResponseEntity<Erro> handleLocalidadeNaoEncontradoException(LogradouroNaoEncontradoException e){
        log.info("M=LocalidadeController.handleLocalidadeNaoEncontradoException, error={}", e.getMessage());

        return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
    }


    class Erro{
        private final String erro;

        public Erro(String erro) {
            this.erro = erro;
        }

        public String getErro() {
            return erro;
        }
    }
}
