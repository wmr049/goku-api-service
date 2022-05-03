package com.goku.domain.address.repository;

import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.entity.Pais;
import com.goku.domain.address.repository.helper.EstadoRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long>, EstadoRepositoryQueries {

    Optional<Estado> findBySiglaAndNome(String sigla, String nome);

    Optional<Estado> findByPaisAndSigla(Pais pais, String sigla);

    Optional<Estado> findBySigla(String sigla);

    Optional<Estado> findById(long id);
}
