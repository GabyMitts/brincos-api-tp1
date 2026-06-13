package br.unitins.topicos1.brincos;

import org.hamcrest.CoreMatchers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.brincos.dto.FormatoDTO;
import br.unitins.topicos1.brincos.dto.FormatoDTOResponse;
import br.unitins.topicos1.brincos.service.FormatoService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
class FormatoResourceTest {
    
    @Inject
    FormatoService formatoService;

    @Test
    void testBuscarTodos() {
        RestAssured.given()
        .when().get("/formato")
        .then()
           .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testIncluirFormato() {
        FormatoDTO dto = new FormatoDTO("Redondo");

        RestAssured.given()
           .contentType(ContentType.JSON)
           .body(dto)
        .when()
           .post("/formato")
        .then()
           .statusCode(200)
              .body("id", CoreMatchers.notNullValue(),
                "nome", CoreMatchers.is("Redondo"));
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testAlterarFormato() {
        FormatoDTO dto = new FormatoDTO("Quadrado");

        FormatoDTOResponse formatoCriado = formatoService.create(dto);

        FormatoDTO dtoAlterar = new FormatoDTO("Retangular");

        RestAssured.given()
           .contentType(ContentType.JSON)
           .body(dtoAlterar)
        .when()
           .put("/formato/" + formatoCriado.id())
        .then()
           .statusCode(204);

        FormatoDTOResponse formatoAlterado = formatoService.findById(formatoCriado.id());
        assertEquals(dtoAlterar.nome(), formatoAlterado.nome());
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testApagarFormato() {
        FormatoDTO dto = new FormatoDTO("Espiral");

        FormatoDTOResponse formatoCriado = formatoService.create(dto);

        RestAssured.given()
        .when()
           .delete("/formato/" + formatoCriado.id())
        .then()
           .statusCode(204);

        FormatoDTOResponse formatoDeletado = formatoService.findById(formatoCriado.id());
        assertNull(formatoDeletado);
    }
}
