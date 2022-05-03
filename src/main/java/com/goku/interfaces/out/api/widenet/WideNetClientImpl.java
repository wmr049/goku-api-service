package com.goku.interfaces.out.api.widenet;

import com.goku.interfaces.out.proxy.service.dto.WideNet;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class WideNetClientImpl implements WideNetClient {

    RestTemplate restTemplate = new RestTemplate();

    final String ROOT_URI = "https://ws.apicep.com/cep.json?code=";

    @Override
    public ResponseEntity<WideNet> getByCEP(String cep) {

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<WideNet> response = restTemplate.exchange(ROOT_URI + cep, HttpMethod.GET, entity, WideNet.class);

        if (response.getBody().getStatus() == 400)
            return new ResponseEntity<WideNet>(HttpStatus.NOT_FOUND);

        return response;
    }

}
