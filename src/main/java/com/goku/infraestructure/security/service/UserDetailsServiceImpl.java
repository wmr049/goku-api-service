package com.goku.infraestructure.security.service;

import com.goku.domain.user.entity.Usuario;
import com.goku.domain.user.repository.UsuarioRepository;
import com.goku.infraestructure.security.UserAuthentication;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Usamos o email, ao invés do username
        Usuario usuario = repository.findByEmail(email).orElseGet(() -> {
            throw new UsernameNotFoundException("User not found with email: " + email);
        });

        return new UserAuthentication(usuario.getId(), usuario.getNomeCompleto(), usuario.getEmail(), usuario.getSenha());
    }
}