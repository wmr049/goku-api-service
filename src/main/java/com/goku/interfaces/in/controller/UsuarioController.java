package com.goku.interfaces.in.controller;

import com.goku.domain.user.service.UsuarioService;
import com.goku.domain.user.dto.request.UsuarioRequest;
import com.goku.domain.user.dto.response.UsuarioResponse;
import com.goku.interfaces.in.api.UsuarioApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Slf4j
public class UsuarioController implements UsuarioApi {

    private final UsuarioService usuarioService;

    @Override
    public ResponseEntity<HttpStatus> novoUsuario(UsuarioRequest usuarioRequest) {
        usuarioService.novoUsuario(usuarioRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UsuarioResponse> buscarClientePorEmail(String email) {
        return ResponseEntity.ok(usuarioService.buscarClientePorEmail(email));
    }
}
