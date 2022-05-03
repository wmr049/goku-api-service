package com.goku.interfaces.in.controller;

import com.goku.AddressServiceApplicationTests;
import com.goku.domain.address.entity.Estado;
import com.goku.domain.address.entity.Pais;
import com.goku.domain.address.repository.filtro.EstadoFiltro;
import com.goku.utils.StringUtil;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static com.goku.utils.Constants.DDI;
import static com.goku.utils.Constants.ESTADO_NOVO;
import static com.goku.utils.Constants.IBGEID;
import static com.goku.utils.Constants.ID;
import static com.goku.utils.Constants.NACIONALIDADE;
import static com.goku.utils.Constants.PAIS;
import static com.goku.utils.Constants.REFERENCE;
import static com.goku.utils.Constants.SIGLA;
import static com.goku.utils.Constants.TIPO_REGIAO;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class EstadoControllerTest extends AddressServiceApplicationTests {


    @Test
    public void deve_procurar_estado_pela_sigla() throws Exception {
        given()
                .pathParam("sigla", "SP")
                .get("/estados/{sigla}")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1),
                        "nome", equalTo(StringUtil.convertUtf8("São Paulo")),
                        "tipoRegiao", equalTo("SUDESTE"));

    }

    @Test
    public void deve_retornar_erro_nao_encontrado_quando_buscar_estado_por_sigla_inexiste() throws Exception {

        given()
                .pathParam("sigla", "MG")
                .get("/estados/{sigla}")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("erro", equalTo("N�o existe estado cadastrado com esta sigla MG"));

    }

    @Test
    public void deve_salvar_novo_estado() throws Exception {

        final Estado estado = mockEstado();

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-Type", ContentType.JSON)
                .body(estado)
                .when()
                .pathParam("pais_id", 2L)
                .post("/estados/{pais_id}")
                .then()
                .log().headers()
                .and()
                .log().body()
                .and()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", equalTo("http://localhost:" + porta + "/estados/2/SF"))
                .body("id", equalTo(6),
                        "nome", equalTo("S�o Francisco"),
                        "sigla", equalTo("SF")
                );

    }


    @Test
    public void nao_deve_salvar_dois_estados_com_mesma_sigla() throws Exception {
        final Estado estado = mockEstado();

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-Type", ContentType.JSON)
                .body(estado)
                .when()
                .pathParam("pais_id", 1L)
                .post("/estados/{pais_id}")
                .then()
                .log().headers()
                .and()
                .log().body()
                .and()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("erro", equalTo("J� existe um estado cadastrado com a sigla 'SP'"));

    }

    @Test
    public void filtrar_pelo_nome_do_estado() throws Exception {

        final EstadoFiltro filtro = new EstadoFiltro();
        filtro.setNome(StringUtil.convertUtf8("�o"));

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-Type", ContentType.JSON)
                .body(filtro)
                .when()
                .post("/estados/filtrar")
                .then()
                .log().headers()
                .and()
                .log().body()
                .and()
                .statusCode(HttpStatus.OK.value())
                .body("id", containsInAnyOrder(1, 3),
                        "nome", containsInAnyOrder(StringUtil.convertUtf8("S�o Paulo"), StringUtil.convertUtf8("Maranh�o")),
                        "tipoRegiao", containsInAnyOrder("SUDESTE", "NORDESTE"));

    }

    private Estado mockEstado() {
        return Estado.builder()
                .id(ID)
                .sigla(SIGLA)
                .tipoRegiao(TIPO_REGIAO)
                .ibgeId(IBGEID)
                .nome(ESTADO_NOVO)
                .reference(REFERENCE)
                .pais(mockPais())
                .build();
    }

    private Pais mockPais() {
        return Pais.builder()
                .nome(PAIS)
                .ddi(DDI)
                .nacionalidade(NACIONALIDADE)
                .build();
    }
}
