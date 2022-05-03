package com.goku.interfaces.out.api.republicavirtual;

import com.goku.interfaces.out.proxy.service.dto.RepublicaVirtual;
import org.springframework.http.ResponseEntity;

public interface RepublicaVirtualClient {

    ResponseEntity<RepublicaVirtual> getByCEP(String cep);
}
