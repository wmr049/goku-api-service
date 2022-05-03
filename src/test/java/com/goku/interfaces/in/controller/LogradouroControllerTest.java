package com.goku.interfaces.in.controller;

import com.goku.AddressServiceApplicationTests;
import com.goku.utils.StringUtil;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class LogradouroControllerTest extends AddressServiceApplicationTests {

    @Test
    public void deve_procurar_logradouro_por_cep() throws Exception {
        given()
                .pathParam("cep", 13050030)
                .get("/logradouros/{cep}")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.OK.value())
                .body("cep", equalTo("13050030"),
                        "nomeAbreviado", equalTo(StringUtil.convertUtf8("Senador Ant�nio Lacerda Franco")),
                        "complemento", equalTo("- de 522/523 ao fim"),
                        "bairro", equalTo("Jardim do Lago"),
                        "localidade", equalTo("Campinas"),
                        "ufe", equalTo("SP")
                );

    }

    @Test
    public void deve_retornar_erro_nao_encontrado_quando_buscar_logradouro_por_cep_inexistente_base_e_proxy() throws Exception {

        given()
                .pathParam("cep", 13145999)
                .get("/logradouros/{cep}")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("erro", equalTo("N�o existe logradouro cadastrado para o CEP 13145999"));

    }

}