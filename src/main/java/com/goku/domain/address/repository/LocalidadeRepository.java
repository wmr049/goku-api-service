package com.goku.domain.address.repository;

import com.goku.domain.address.entity.Localidade;
import com.goku.domain.address.repository.helper.LocalidadeRepositoryQueries;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocalidadeRepository extends JpaRepository<Localidade, Long>, LocalidadeRepositoryQueries {

    Optional<List<Localidade>> findByEstadoId(Long id);

    Page<Localidade> findByEstadoId(Long id, Pageable pageable);

    Optional<Localidade> findByNomeAndEstadoSigla(String nome, String sigla);
}
