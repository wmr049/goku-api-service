package com.goku.interfaces.out.api.widenet;

import com.goku.interfaces.out.proxy.service.dto.WideNet;
import org.springframework.http.ResponseEntity;

public interface WideNetClient {
    ResponseEntity<WideNet> getByCEP(String cep);
}
