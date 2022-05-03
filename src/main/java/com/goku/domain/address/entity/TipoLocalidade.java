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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Entity
@Where(clause = "flg_active=1")
@Table(name = "TIPO_LOCALIDADE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoLocalidade extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String reference;
    private String codigo;

    @OneToMany(mappedBy = "tipoLocalidade")
    private List<Localidade> localidades;

    @JsonIgnore
    public List<Localidade> getLocalidades() {
        return localidades;
    }

}
