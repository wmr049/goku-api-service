package com.goku.domain.user.service;

import com.goku.domain.user.dto.request.UsuarioRequest;
import com.goku.domain.user.dto.response.UsuarioResponse;
import com.goku.domain.user.entity.Usuario;

public interface UsuarioService {
    void novoUsuario(UsuarioRequest usuarioRequest);

    Usuario getAuthenticatedDetails();
    void isAdmin();

    UsuarioResponse buscarClientePorEmail(String email);
}
