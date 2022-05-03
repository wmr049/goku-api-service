package com.goku.interfaces.in.controller;

import com.goku.domain.address.dto.response.LocalidadeResponse;
import com.goku.domain.address.entity.Localidade;
import com.goku.domain.address.repository.filtro.LocalidadeFiltro;
import com.goku.domain.address.service.LocalidadeService;
import com.goku.infraestructure.config.exception.LocalidadeNaoEncontradoException;
import com.goku.interfaces.in.api.LocalidadeApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/localidade")
@RequiredArgsConstructor
@Slf4j
public class LocalidadeController implements LocalidadeApi {


    private final LocalidadeService localidadeService;


    @Override
    public ResponseEntity<List<LocalidadeResponse>> buscarPorEstadoId(Long estadoId) throws LocalidadeNaoEncontradoException {
        log.info("M=LocalidadeController.buscarPorEstadoId, estadoId={}", estadoId);
        return ResponseEntity.ok(localidadeService.filtrarPorEstado(estadoId));
    }

    @Override
    public ResponseEntity<Page<Localidade>> buscarPorEstadoIdPaginado(Long estadoId, int page, int size) throws LocalidadeNaoEncontradoException {
        log.info("M=LocalidadeController.buscarPorEstadoIdPaginado, estadoId={}", estadoId);
        return ResponseEntity.ok(localidadeService.filtrarPorEstadoPaginado(estadoId, page, size));
    }

    @Override
    public ResponseEntity<List<Localidade>> filtrar(LocalidadeFiltro filtro) {
        log.info("M=LocalidadeController.filtrar, filtro={}", filtro);
        return ResponseEntity.ok(localidadeService.filtrar(filtro));
    }

    @ExceptionHandler({LocalidadeNaoEncontradoException.class})
    public ResponseEntity<Erro> handleLocalidadeNaoEncontradoException(LocalidadeNaoEncontradoException e){
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
