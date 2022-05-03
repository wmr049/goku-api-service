package com.goku.domain.address.repository;

import com.goku.domain.address.entity.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {

    Optional<Logradouro> findByCep(String cep);

}
