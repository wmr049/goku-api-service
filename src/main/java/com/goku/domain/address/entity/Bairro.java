package com.goku.domain.address.entity;

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
@Table(name = "BAIRRO")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bairro extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "localidade_id")
    private Localidade Localidade;

    private String nome;
    private String nomeAbreviado;

}
