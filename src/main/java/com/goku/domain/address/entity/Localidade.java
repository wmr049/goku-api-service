package com.goku.domain.address.entity;

import com.goku.infraestructure.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Entity
@Where(clause = "flg_active=1")
@Table(name = "LOCALIDADE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Localidade extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "tipo_localidade_id")
    private TipoLocalidade tipoLocalidade;

    private String nomeAbreviado;

    private String nome;

    private String cep;

    private String temp;

    private String ibgeId;

    private String naturalidade;

    private String ddd;

    private String conjuncao;

    private String reference;

    @Column(name="ibge_id_2")
    private String ibgeId2;

    private int situacao;

    private Long municipioId;

    private Date populacaoData;

    private Long municipioPaiId;

    @Column(columnDefinition = "int default 0")
    private int populacao;

}
