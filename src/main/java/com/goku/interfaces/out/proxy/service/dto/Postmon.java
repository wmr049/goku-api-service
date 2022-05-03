package com.goku.interfaces.out.proxy.service.dto;

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
public class Postmon {

    private String bairro;
    private String cidade;
    private String logradouro;
    private String cep;
    private String estado;

}
