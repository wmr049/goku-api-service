package com.goku.domain.user.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.goku.domain.address.entity.Localidade;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioRequest {

    @JsonIgnore
    private Integer id;

    private String nomeCompleto;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;


    private Boolean isAdmin;

    private String senha;

    private Integer idade;

    private Localidade cidade;
}
