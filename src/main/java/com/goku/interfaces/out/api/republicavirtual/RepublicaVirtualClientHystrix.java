package com.goku.interfaces.out.api.republicavirtual;

import com.goku.interfaces.out.proxy.service.dto.RepublicaVirtual;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;

public class RepublicaVirtualClientHystrix implements RepublicaVirtualClient {

    @Override
    @HystrixCommand(groupKey = "addressGroup", fallbackMethod = "fallBackCall")
    public ResponseEntity<RepublicaVirtual> getByCEP(String cep) {
        return new RepublicaVirtualClientImpl().getByCEP(cep);
    }

    public String fallBackCall() {
        return "FALHA NA CHAMADA DO SERVIÇO REPUBLICA VIRTUAL! - FALLING BACK";
    }

}
