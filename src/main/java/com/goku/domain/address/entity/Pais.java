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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Entity
@Where(clause = "flg_active=1")
@Table(name = "PAIS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pais extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    private String conjuncao;

    @Column(length = 50, nullable = true)
    private String nacionalidade;

    @Column(length = 5, nullable = false)
    private String ddi;

    private String iso;

    @OneToMany(mappedBy = "pais")
    private List<Estado> estado;
}
