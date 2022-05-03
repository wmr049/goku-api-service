package com.goku.interfaces.out.api.postmon;

import com.goku.interfaces.out.proxy.service.dto.Postmon;
import org.springframework.http.ResponseEntity;

public interface PostmonClient {
    ResponseEntity<Postmon> getByCEP(String cep);
}
