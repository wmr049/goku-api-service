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

@Getter
@Entity
@Where(clause = "flg_active=1")
@Table(name = "ESTADO")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estado extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3, nullable = false)
    private String sigla;

    @Column(length = 20, nullable = false)
    private String tipoRegiao;

    @Column(length = 20, nullable = true)
    private String ibgeId;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

/*	@OneToMany(mappedBy = "estado")
    private List<Localidade> localidade;*/

    @Column(length = 50, nullable = false)
    private String nome;

    private String conjuncao;

    private String reference;


}
