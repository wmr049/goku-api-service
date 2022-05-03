package com.goku.interfaces.out.api.viacep;

import com.goku.interfaces.out.proxy.service.dto.ViaCep;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;

public class ViaCepClientHystrix implements ViaCepClient{

    @Override
    @HystrixCommand(groupKey = "addressGroup", fallbackMethod = "fallBackCall")
    public ResponseEntity<ViaCep> getByCEP(String cep) {
        return new ViaCepClientImpl().getByCEP(cep);
    }

    public String fallBackCall() {
        return "FALHA NA CHAMADA DO SERVIÇO VIACEP! - FALLING BACK";
    }

}
