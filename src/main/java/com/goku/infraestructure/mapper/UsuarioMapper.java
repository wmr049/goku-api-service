package com.goku.infraestructure.mapper;

import com.goku.domain.user.dto.request.UsuarioRequest;
import com.goku.domain.user.dto.response.UsuarioResponse;
import com.goku.domain.user.entity.Usuario;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UsuarioMapper {

    @Mapping(target = "senha", ignore = true)
    UsuarioRequest toRequest(Usuario usuario);

    UsuarioResponse toResponse(Usuario usuario);

    Usuario fromRequest(UsuarioRequest usuarioRequest);

}
