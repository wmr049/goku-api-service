package com.goku.domain.address.repository.filtro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaisFiltro {
    private Long id;
    private String nome;
    private String ddi;
    private String tipoRegiao;

    private String iso;
}
