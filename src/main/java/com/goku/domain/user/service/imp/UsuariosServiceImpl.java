package com.goku.domain.user.service.imp;

import com.goku.domain.address.service.LocalidadeService;
import com.goku.domain.user.dto.request.UsuarioRequest;
import com.goku.domain.user.dto.response.UsuarioResponse;
import com.goku.domain.user.entity.Usuario;
import com.goku.domain.user.repository.UsuarioRepository;
import com.goku.domain.user.service.UsuarioService;
import com.goku.infraestructure.config.exception.UserPermissionsException;
import com.goku.infraestructure.mapper.UsuarioMapper;
import com.goku.infraestructure.security.UserAuthentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuariosServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    private final LocalidadeService localidadeService;

    private final BCryptPasswordEncoder B_CRIPT = new BCryptPasswordEncoder();

    @Override
    public void novoUsuario(UsuarioRequest usuarioRequest) {
        this.isAdmin();

        try {
            if (usuarioRepository.findByEmail(usuarioRequest.getEmail()).isPresent()) {
                throw new RuntimeException("Já existe um usuário com o mesmo e-mail cadastrado.");
            }

            Usuario usuario = this.usuarioMapper.fromRequest(usuarioRequest);

            usuario.setCidade(localidadeService.filtrarPorNomeEEstadoSigla(usuarioRequest.getCidade().getNome(), usuarioRequest.getCidade().getEstado().getSigla()));
            usuario.setSenha(B_CRIPT.encode(usuarioRequest.getSenha()));

            this.usuarioRepository.saveAndFlush(usuario);

        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar novo cliente: " + e.getMessage());
        }
    }


    public static UserAuthentication getAuthenticated() {
        return (UserAuthentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public Usuario getAuthenticatedDetails() {
        try {
            return this.usuarioRepository.findById(getAuthenticated().getID().longValue()).get();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void isAdmin() {
        if (!this.getAuthenticatedDetails().getIsAdmin()) {
            throw new UserPermissionsException("Usuário sem permissão para executar a operação.");
        }
    }

    @Override
    public UsuarioResponse buscarClientePorEmail(String email) {
        if (!email.equals(getAuthenticated().getUsername())) {
            this.isAdmin();
        }

        return usuarioMapper.toResponse(usuarioRepository.findByEmail(email).orElseGet(() -> {
            throw new NoSuchElementException("Nenhum usuário foi encontrado com o email: " + email + ".");
        }));
    }
}
