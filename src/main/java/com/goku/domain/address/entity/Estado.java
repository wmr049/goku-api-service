package com.goku.domain.address.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="estado")
public class Estado {
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

    /*	public List<Localidade> getLocalidade() {
            return localidade;
        }

        public void setLocalidade(List<Localidade> localidade) {
            this.localidade = localidade;
        }
        */
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }


    public String getTipoRegiao() {
        return tipoRegiao;
    }

    public void setTipoRegiao(String tipoRegiao) {
        this.tipoRegiao = tipoRegiao;
    }

    public String getIbgeId() {
        return ibgeId;
    }

    public void setIbgeId(String ibgeId) {
        this.ibgeId = ibgeId;
    }

    @JsonIgnore
    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConjuncao() {
        return conjuncao;
    }

    public void setConjuncao(String conjuncao) {
        this.conjuncao = conjuncao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Estado other = (Estado) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
