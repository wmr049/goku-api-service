package com.goku.interfaces.out.api.postmon;

import com.goku.interfaces.out.proxy.service.dto.Postmon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PostmonClientImpl implements PostmonClient {

    RestTemplate restTemplate = new RestTemplate();

    final String ROOT_URI = "https://api.postmon.com.br/v1/cep/";

    @Override
    public ResponseEntity<Postmon> getByCEP(String cep) {

        try {
            return restTemplate.getForEntity(ROOT_URI + cep , Postmon.class);
        }catch(Exception e) {
            return new ResponseEntity<Postmon>(HttpStatus.NOT_FOUND);
        }
    }
}
