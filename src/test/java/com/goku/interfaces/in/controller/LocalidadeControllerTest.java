package com.goku.interfaces.in.controller;

import com.goku.AddressServiceApplicationTests;
import com.goku.domain.address.repository.filtro.LocalidadeFiltro;
import com.goku.utils.StringUtil;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class LocalidadeControllerTest extends AddressServiceApplicationTests {

    @Test
    public void deve_procurar_localidade_por_estadoId() throws Exception {
        given()
                .pathParam("estadoId",1)
                .get("/localidades/{estadoId}")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.OK.value())
                .body("id", containsInAnyOrder(1,6,7,8,9,10,11,12),
                        "nome", containsInAnyOrder("Areias","Araras",
                                StringUtil.convertUtf8("Arax�s (Presidente Bernardes)"),"Arcadas (Amparo)",
                                StringUtil.convertUtf8("Arco-�ris"),"Arealva","Campinas",
                                StringUtil.convertUtf8("Paul�nia")),
                        "estado.tipoRegiao", containsInAnyOrder("SUDESTE","SUDESTE","SUDESTE","SUDESTE","SUDESTE","SUDESTE","SUDESTE","SUDESTE"));

    }


    @Test
    public void deve_procurar_localidade_por_nome_estadosigla() throws Exception {
        given()
                .pathParam("nome","Campinas")
                .pathParam("estadoSigla","SP")
                .get("/localidades/{nome}/{estadoSigla}")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(11),
                        "estado.nome", equalTo(StringUtil.convertUtf8("S�o Paulo")),
                        "nome", equalTo("Campinas"),
                        "estado.tipoRegiao", equalTo("SUDESTE"));
    }



    @Test
    public void deve_retornar_erro_nao_encontrado_quando_buscar_localidade_por_estadoid_inexiste() throws Exception {

        given()
                .pathParam("estadoId",99999)
                .get("/localidades/{estadoId}")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("erro", equalTo("N�o existe localidade cadastrado para o Estado 99999"));

    }

    @Test
    public void filtrar_pelo_nome_da_localidade() throws Exception {

        final LocalidadeFiltro filtro = new LocalidadeFiltro();
        filtro.setNome("Ara");

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-Type", ContentType.JSON)
                .body(filtro)
                .when()
                .post("/localidades/filtrar")
                .then()
                .log().headers()
                .and()
                .log().body()
                .and()
                .statusCode(HttpStatus.OK.value())
                .body("id", containsInAnyOrder (2,6,7),
                        "nome", containsInAnyOrder("Arapixi","Araras",StringUtil.convertUtf8("Arax�s (Presidente Bernardes)")),
                        "cep", containsInAnyOrder("19310000",null,null));

    }

}