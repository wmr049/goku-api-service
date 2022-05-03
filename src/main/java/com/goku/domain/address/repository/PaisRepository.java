package com.goku.domain.address.repository;

import com.goku.domain.address.entity.Pais;
import com.goku.domain.address.repository.helper.PaisRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaisRepository extends JpaRepository<Pais, Long>, PaisRepositoryQueries {

    Optional<Pais> findByEstadoSigla(String sigla);

    //JPA funciona dessa forma porem para exemplificar a query iremos fazer uma altera��o no nome do metodo
    //Optional<Pais> findByEstadoSiglaAndEstadoTipoRegiao(String sigla, String tipoRegiao);

    @Query("SELECT bean FROM Pais bean JOIN bean.estado uf where uf.sigla = :sigla and uf.tipoRegiao = :tipoRegiao")
    Optional<Pais> findByEstadosSiglaAndEstadosTipoRegiao(@Param("sigla") String sigla, @Param("tipoRegiao") String tipoRegiao);



}
