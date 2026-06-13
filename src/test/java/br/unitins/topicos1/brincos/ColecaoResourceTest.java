package br.unitins.topicos1.brincos;

import org.hamcrest.CoreMatchers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.brincos.dto.ColecaoDTO;
import br.unitins.topicos1.brincos.dto.ColecaoDTOResponse;
import br.unitins.topicos1.brincos.service.ColecaoService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
class ColecaoResourceTest {
    
    @Inject
    ColecaoService colecaoService;

    @Test
    void testBuscarTodos() {
        RestAssured.given()
        .when().get("/colecao")
        .then()
           .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testIncluirColecao() {
        ColecaoDTO dto = new ColecaoDTO("Primavera", null);

        RestAssured.given()
           .contentType(ContentType.JSON)
           .body(dto)
        .when()
           .post("/colecao")
        .then()
           .statusCode(200)
              .body("id", CoreMatchers.notNullValue(),
                "nome", CoreMatchers.is("Primavera"));
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testAlterarColecao() {
        ColecaoDTO dto = new ColecaoDTO("Outono", null);

        ColecaoDTOResponse colecaoCriada = colecaoService.create(dto);

        ColecaoDTO dtoAlterar = new ColecaoDTO("Inverno", null);

        RestAssured.given()
           .contentType(ContentType.JSON)
           .body(dtoAlterar)
        .when()
           .put("/colecao/" + colecaoCriada.id())
        .then()
           .statusCode(204);

        ColecaoDTOResponse colecaoAlterada = colecaoService.findById(colecaoCriada.id());
        assertEquals(dtoAlterar.nome(), colecaoAlterada.nome());
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testApagarColecao() {
        ColecaoDTO dto = new ColecaoDTO("Verão", null);

        ColecaoDTOResponse colecaoCriada = colecaoService.create(dto);

        RestAssured.given()
        .when()
           .delete("/colecao/" + colecaoCriada.id())
        .then()
           .statusCode(204);

        ColecaoDTOResponse colecaoDeletada = colecaoService.findById(colecaoCriada.id());
        assertNull(colecaoDeletada);
    }
}
