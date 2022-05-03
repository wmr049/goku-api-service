package com.goku.domain.address.dto.response;

import com.goku.domain.address.entity.Estado;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EstadoResponse {

    private Long id;
    private String sigla;
    private String tipoRegiao;
    private String ibgeId;
    private String nome;
    private String reference;

    public static EstadoResponse from(Estado estado) {
        return EstadoResponse.builder()
                .id(estado.getId())
                .sigla(estado.getSigla())
                .tipoRegiao(estado.getTipoRegiao())
                .ibgeId(estado.getIbgeId())
                .nome(estado.getNome())
                .reference(estado.getReference())
                .build();
    }

    public EstadoResponse(Estado estado) {
        this.id = estado.getId();
        this.sigla = estado.getSigla();
        this.tipoRegiao = estado.getTipoRegiao();
        this.ibgeId = estado.getIbgeId();
        this.nome = estado.getNome();
        this.reference = estado.getReference();
    }

    public EstadoResponse() {
    }

    public EstadoResponse(Long id, String sigla, String tipoRegiao, String ibgeId, String nome, String reference) {
        this.id = id;
        this.sigla = sigla;
        this.tipoRegiao = tipoRegiao;
        this.ibgeId = ibgeId;
        this.nome = nome;
        this.reference = reference;
    }
}
