package com.goku.interfaces.in.api;

import com.goku.domain.user.dto.request.UsuarioRequest;
import com.goku.domain.user.dto.response.UsuarioResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface UsuarioApi {

    @PostMapping("/novo-usuario")
    @ApiOperation(value = "Salvar estado.", httpMethod = "POST", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<HttpStatus> novoUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest);

    @GetMapping(path = "/buscar-cliente-por-email/{email}")
    @ApiOperation(value = "Busca um cliente por ID", authorizations = @Authorization(value = "Bearer"))
    ResponseEntity<UsuarioResponse> buscarClientePorEmail(@PathVariable("email") String email);

}
