package com.goku.interfaces.out.api.widenet;

import com.goku.interfaces.out.proxy.service.dto.WideNet;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;

public class WideNetClientHystrix implements WideNetClient {

    @Override
    @HystrixCommand(groupKey = "addressGroup", fallbackMethod = "fallBackCall")
    public ResponseEntity<WideNet> getByCEP(String cep) {
        return new WideNetClientImpl().getByCEP(cep);
    }

    public String fallBackCall() {
        return "FALHA NA CHAMADA DO SERVI�O WIDENET! - FALLING BACK";
    }

}
