package com.goku.domain.user.entity;


import com.goku.domain.address.entity.Localidade;
import com.goku.infraestructure.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Getter
@Setter
@Entity
@Where(clause = "flg_active=1")
@Table(name = "BAIRRO")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;

    @Column()
    private String email;

    @Column(name = "DATA_NASCIMENTO")
    private Date dataNascimento;

    @OneToOne
    @JoinColumn(name = "localidade_id")
    private Localidade cidade;

    @Column(name = "IS_ADMIN")
    private Boolean isAdmin;

    @Column()
    private String senha;

    @Transient
    private Integer idade;

}
