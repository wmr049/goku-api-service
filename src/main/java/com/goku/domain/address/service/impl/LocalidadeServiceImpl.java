package com.goku.domain.address.service.impl;

import com.goku.domain.address.dto.response.LocalidadeResponse;
import com.goku.domain.address.entity.Localidade;
import com.goku.domain.address.repository.LocalidadeRepository;
import com.goku.domain.address.repository.filtro.LocalidadeFiltro;
import com.goku.domain.address.service.LocalidadeService;
import com.goku.domain.user.service.UsuarioService;
import com.goku.infraestructure.config.exception.LocalidadeNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalidadeServiceImpl implements LocalidadeService {

    private LocalidadeRepository localidadeRepository;
    private UsuarioService usuarioService;

    @Override
    public List<LocalidadeResponse> filtrarPorEstado(Long id) throws LocalidadeNaoEncontradoException {
        usuarioService.isAdmin();

        final Optional<List<Localidade>> optional = localidadeRepository.findByEstadoId(id);
        List<Localidade> localidades = optional.orElseThrow(() -> new LocalidadeNaoEncontradoException("Não existe localidade cadastrado para o Estado " + id));
        return LocalidadeResponse.listOf(localidades);
    }

    @Override
    public Page<Localidade> filtrarPorEstadoPaginado(Long id, int page, int size) throws LocalidadeNaoEncontradoException {
        usuarioService.isAdmin();

        Pageable pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
        return localidadeRepository.findByEstadoId(id, pageRequest);
    }

    @Override
    public Localidade filtrarPorNomeEEstadoSigla(String nome, String sigla) throws LocalidadeNaoEncontradoException {
        usuarioService.isAdmin();

        final Optional<Localidade> optional = localidadeRepository.findByNomeAndEstadoSigla(nome, sigla);
        return optional.orElseThrow(() -> new LocalidadeNaoEncontradoException("Não existe localidade cadastrado para o Estado " + sigla));
    }

    @Override
    public List<Localidade> filtrar(LocalidadeFiltro filtro) {
        usuarioService.isAdmin();
        return localidadeRepository.filtrar(filtro);
    }

}
