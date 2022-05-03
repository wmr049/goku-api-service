package com.goku.interfaces.in.api;

import com.goku.domain.address.entity.Logradouro;
import com.goku.infraestructure.config.exception.LogradouroNaoEncontradoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Api(value = "Logradouro")
public interface LogradouroApi {

    @GetMapping("/logradouros/{cep}")
    @ApiOperation(value = "Buscar endereço pelo CEP.", httpMethod = "GET", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<Logradouro> buscarPorCEP(@PathVariable("cep") String cep) throws LogradouroNaoEncontradoException;
}
