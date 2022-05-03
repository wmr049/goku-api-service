package com.goku.interfaces.out.api.viacep;

import com.goku.interfaces.out.proxy.service.dto.ViaCep;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ViaCepClientImpl implements ViaCepClient {

    RestTemplate restTemplate = new RestTemplate();

    final String ROOT_URI = "https://viacep.com.br/ws/";

    @Override
    public ResponseEntity<ViaCep> getByCEP(String cep) {

        ResponseEntity<ViaCep> response = restTemplate.getForEntity(ROOT_URI + cep + "/json/", ViaCep.class);

        if (response.getBody().getErro() != null)
            return new ResponseEntity<ViaCep>(HttpStatus.NOT_FOUND);

        return response;
    }

}