package com.goku.domain.address.service.impl;

import com.goku.domain.address.dto.response.EstadoResponse;
import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.repository.EstadoRepository;
import com.goku.domain.address.repository.filtro.EstadoFiltro;
import com.goku.domain.address.service.EstadoService;
import com.goku.domain.user.service.UsuarioService;
import com.goku.infraestructure.config.exception.EstadoNaoEncontradoException;
import com.goku.infraestructure.config.exception.UnicidadeEstadoException;
import com.goku.infraestructure.config.exception.UnicidadePaisEstadoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstadoServiceImpl implements EstadoService {

    private EstadoRepository estadoRepository;

    private UsuarioService usuarioService;


    @Override
    public EstadoResponse salvar(Estado estado) throws UnicidadeEstadoException, UnicidadePaisEstadoException {

        usuarioService.isAdmin();

        Optional<Estado> estadoOptional = estadoRepository.findBySiglaAndNome(estado.getSigla(), estado.getNome());
        if (estadoOptional.isPresent()) {
            throw new UnicidadeEstadoException("Já existe um estado cadastrado com a sigla '" + estado.getSigla() + "'");
        }

        estadoOptional = estadoRepository.findByPaisAndSigla(estado.getPais(), estado.getSigla());
        if (estadoOptional.isPresent()) {
            throw new UnicidadePaisEstadoException("Já existe um estado cadastrado para esse pais ");
        }

        return EstadoResponse.from(estadoRepository.save(estado));
    }

    @Override
    public EstadoResponse filtrarPorUF(String sigla) {
        usuarioService.isAdmin();

        final Optional<Estado> optionalEstado = estadoRepository.findBySigla(sigla);
        Estado estado = optionalEstado.orElseThrow(() -> new EstadoNaoEncontradoException("Não existe estado cadastrado com esta sigla " + sigla));

        return EstadoResponse.from(estadoRepository.save(estado));
    }

    @Override
    public EstadoResponse filtrarPorId(long id) {
        usuarioService.isAdmin();

        final Optional<Estado> optional = estadoRepository.findById(id);
        Estado estado = optional.orElseThrow(() -> new EstadoNaoEncontradoException("Não existe estado cadastrado com esta sigla " + id));
        return EstadoResponse.from(estadoRepository.save(estado));
    }

    @Override
    public List<EstadoResponse> filtrar(EstadoFiltro filtro) {
        usuarioService.isAdmin();

        return estadoRepository.filtrar(filtro).stream().map(EstadoResponse::new).collect(Collectors.toList());
    }
}
