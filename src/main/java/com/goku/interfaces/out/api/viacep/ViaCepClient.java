package com.goku.interfaces.out.api.viacep;

import com.goku.interfaces.out.proxy.service.dto.ViaCep;
import org.springframework.http.ResponseEntity;

public interface ViaCepClient {
    ResponseEntity<ViaCep> getByCEP(String cep);
}
