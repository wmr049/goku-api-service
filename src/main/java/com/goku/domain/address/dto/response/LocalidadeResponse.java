package com.goku.domain.address.dto.response;

import com.goku.domain.address.entity.Localidade;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class LocalidadeResponse {

    private Long id;

    private String nome;

    private String cep;

    private String ibgeId;

    private String naturalidade;

    private String ddd;


    public static List<LocalidadeResponse> listOf(List<Localidade> localidades) {

        List<LocalidadeResponse> localidadeResponses = new ArrayList<>();

        for (Localidade localidade : localidades) {
            localidadeResponses.add(LocalidadeResponse.builder()
                    .id(localidade.getId())
                    .nome(localidade.getNome())
                    .cep(localidade.getCep())
                    .ibgeId(localidade.getIbgeId())
                    .naturalidade(localidade.getNaturalidade())
                    .ddd(localidade.getDdd())
                    .build());
        }

        return localidadeResponses;
    }

    public static LocalidadeResponse of(Localidade localidade) {
        return LocalidadeResponse.builder()
                .id(localidade.getId())
                .nome(localidade.getNome())
                .cep(localidade.getCep())
                .ibgeId(localidade.getIbgeId())
                .naturalidade(localidade.getNaturalidade())
                .build();
    }
}
