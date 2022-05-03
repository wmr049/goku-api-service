package com.goku.interfaces.in.api;

import com.goku.domain.address.dto.response.LocalidadeResponse;
import com.goku.domain.address.entity.Localidade;
import com.goku.domain.address.repository.filtro.LocalidadeFiltro;
import com.goku.infraestructure.config.exception.LocalidadeNaoEncontradoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value = "Localidade")
public interface LocalidadeApi {

    @GetMapping("/localidades/{estadoId}")
    @ApiOperation(value = "Buscar estado por sigla do estado.", httpMethod = "GET", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<List<LocalidadeResponse>> buscarPorEstadoId(@ApiParam(value = "ID do Banco do Estado.") @PathVariable("estadoId") Long estadoId) throws LocalidadeNaoEncontradoException;

    @GetMapping("/localidades/{nome}/{estadoSigla}")
    @ApiOperation(value = "Buscar estado por sigla do estado paginado.", httpMethod = "GET", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<Page<Localidade>> buscarPorEstadoIdPaginado(
            @ApiParam(value = "ID do Banco do Estado.") @PathVariable("estadoId") Long estadoId,
            @ApiParam(value = "pagina", required = false, defaultValue = "0") @PathVariable("page")  int page,
            @ApiParam(value = "quantidade por pagina", required = false, defaultValue = "10") @PathVariable("size") int size) throws LocalidadeNaoEncontradoException;


    @PostMapping("/localidades/filtrar")
    @ApiOperation(value = "Realizar filtro.", httpMethod = "POST", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<List<Localidade>> filtrar(@RequestBody LocalidadeFiltro filtro);
}
