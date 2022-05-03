package com.goku.interfaces.in.controller;

import com.goku.domain.address.dto.response.EstadoResponse;
import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.repository.filtro.EstadoFiltro;
import com.goku.domain.address.service.EstadoService;
import com.goku.infraestructure.config.exception.EstadoNaoEncontradoException;
import com.goku.infraestructure.config.exception.UnicidadeEstadoException;
import com.goku.infraestructure.config.exception.UnicidadePaisEstadoException;
import com.goku.interfaces.in.api.EstadoApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/estado")
@RequiredArgsConstructor
@Slf4j
public class EstadoController implements EstadoApi {

    private final EstadoService estadoService;

    @Override
    public ResponseEntity<EstadoResponse> buscarPorSigla(String sigla) throws EstadoNaoEncontradoException {
        log.info("M=EstadoController.buscarPorSigla, sigla={}", sigla);
        return ResponseEntity.ok(estadoService.filtrarPorUF(sigla));
    }

    @Override
    public ResponseEntity<EstadoResponse> salvar(Estado estado, Long pais_id, HttpServletResponse response) throws UnicidadeEstadoException, UnicidadePaisEstadoException {
        log.info("M=EstadoController.salvar, estado={}, pais_id={}", estado.toString(), pais_id);
        return ResponseEntity.ok(estadoService.salvar(estado));
    }

    @Override
    public ResponseEntity<List<EstadoResponse>> filtrar(EstadoFiltro filtro) {
        log.info("M=EstadoController.filtrar, filtro={}", filtro);
        return ResponseEntity.ok(estadoService.filtrar(filtro));
    }

    @ExceptionHandler({EstadoNaoEncontradoException.class})
    public ResponseEntity<Erro> handleEstandoNaoEncontradoException(EstadoNaoEncontradoException e){
        log.info("M=EstadoController.handleEstandoNaoEncontradoException, error={}", e.getMessage());
        return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UnicidadeEstadoException.class})
    public ResponseEntity<Erro> handleUnicidadeEstadoException(UnicidadeEstadoException e){
        log.info("M=EstadoController.handleUnicidadeEstadoException, error={}", e.getMessage());
        return new ResponseEntity<>(new Erro(e.getMessage()),HttpStatus.BAD_REQUEST);
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
