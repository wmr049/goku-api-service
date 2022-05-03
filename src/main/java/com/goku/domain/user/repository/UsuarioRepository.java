package com.goku.domain.user.repository;

import com.goku.domain.user.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM CLIENTE WHERE EMAIL = :email", nativeQuery = true)
    Optional<Usuario> findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM CLIENTE WHERE NOME_COMPLETO = :nomeCompleto", nativeQuery = true)
    Optional<Usuario> findByNomeCompleto(@Param("nomeCompleto") String nomeCompleto);
}
