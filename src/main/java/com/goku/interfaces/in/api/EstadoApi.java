package com.goku.interfaces.in.api;

import com.goku.domain.address.dto.response.EstadoResponse;
import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.entity.Logradouro;
import com.goku.domain.address.repository.filtro.EstadoFiltro;
import com.goku.infraestructure.config.exception.EstadoNaoEncontradoException;
import com.goku.infraestructure.config.exception.LogradouroNaoEncontradoException;
import com.goku.infraestructure.config.exception.UnicidadeEstadoException;
import com.goku.infraestructure.config.exception.UnicidadePaisEstadoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(value = "Estado")
public interface EstadoApi {

    @GetMapping("/estados/{sigla}")
    @ApiOperation(value = "Buscar estado por sigla.", httpMethod = "GET", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<EstadoResponse> buscarPorSigla(@PathVariable("sigla") String sigla) throws EstadoNaoEncontradoException;

    @PostMapping("/estados/{pais_id}")
    @ApiOperation(value = "Salvar estado.", httpMethod = "POST", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<EstadoResponse> salvar(@RequestBody Estado estado, @PathVariable("pais_id") Long pais_id, HttpServletResponse response) throws UnicidadeEstadoException, UnicidadePaisEstadoException;

    @PostMapping("/estados/filtrar")
    @ApiOperation(value = "Realizar filtro.", httpMethod = "POST", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<List<EstadoResponse>> filtrar(@RequestBody EstadoFiltro filtro);

}
