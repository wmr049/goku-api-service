package com.goku.interfaces.out.api.viacep;

import org.springframework.cloud.openfeign.FeignClient;
import com.goku.interfaces.out.proxy.service.dto.ViaCep;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url="https://viacep.com.br/ws/", name = "viacep")
public interface ViaCepClientFeign {
    @RequestMapping(method = RequestMethod.GET, value = "{cep}/json")
    ResponseEntity<ViaCep> getByCEP(@PathVariable("cep") String cep);
}
