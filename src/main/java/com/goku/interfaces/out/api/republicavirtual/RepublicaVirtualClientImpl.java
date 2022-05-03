package com.goku.interfaces.out.api.republicavirtual;

import com.goku.interfaces.out.proxy.service.dto.RepublicaVirtual;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RepublicaVirtualClientImpl implements RepublicaVirtualClient {


    RestTemplate restTemplate = new RestTemplate();

    final String ROOT_URI = "http://cep.republicavirtual.com.br/web_cep.php?cep=";

    @Override
    public ResponseEntity<RepublicaVirtual> getByCEP(String cep) {

        ResponseEntity<RepublicaVirtual> response = restTemplate.getForEntity(ROOT_URI + cep + "&formato=json", RepublicaVirtual.class);

        if (response.getBody().getResultado() == 0)
            return new ResponseEntity<RepublicaVirtual>(HttpStatus.NOT_FOUND);

        return response;
    }

}