package com.goku.domain.address.entity;

import com.goku.infraestructure.entity.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class TipoLogradouro extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String codigo;

    @OneToMany(mappedBy = "tipoLogradouro")
    private List<Logradouro> logradouros;
}
