package com.goku.interfaces.out.api.postmon;

import com.goku.interfaces.out.proxy.service.dto.Postmon;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;

public class PostmonClientHystrix implements PostmonClient{

    @Override
    @HystrixCommand(groupKey = "addressGroup", fallbackMethod = "fallBackCall")
    public ResponseEntity<Postmon> getByCEP(String cep) {
        return new PostmonClientImpl().getByCEP(cep);
    }

    public String fallBackCall() {
        return "FALHA NA CHAMADA DO SERVIÇO POSTMON! - FALLING BACK";
    }

}
