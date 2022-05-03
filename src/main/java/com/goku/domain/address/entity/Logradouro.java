package com.goku.domain.address.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goku.infraestructure.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Entity
@Where(clause = "flg_active=1")
@Table(name = "LOGRADOURO")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Logradouro extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="logradouro_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bairro_inicio_id")
    @JsonIgnore
    private Bairro bairroInicio;

    @ManyToOne
    @JoinColumn(name = "bairro_fim_id")
    @JsonIgnore
    private Bairro bairroFim;

    @ManyToOne
    @JoinColumn(name = "tipo_logradouro_id")
    private TipoLogradouro tipoLogradouro;

    private String nomeAbreviado;


    private String nome;
    private String cep;
    private String complemento;


    private String statusTipoLog;

    private String nomeSemAcento;

    private String correio;

    private String referencia;

    private String bairro;
    private String localidade;
    private String ufe;
}
