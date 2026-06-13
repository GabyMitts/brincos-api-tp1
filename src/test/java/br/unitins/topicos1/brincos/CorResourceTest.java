package br.unitins.topicos1.brincos;

import org.hamcrest.CoreMatchers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.brincos.dto.CorDTO;
import br.unitins.topicos1.brincos.dto.CorDTOResponse;
import br.unitins.topicos1.brincos.service.CorService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
class CorResourceTest {
    
    @Inject
    CorService corService;

    @Test
    void testBuscarTodos() {
        RestAssured.given()
        .when().get("/cor")
        .then()
           .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testIncluirCor() {
        CorDTO dto = new CorDTO("Prata");

        RestAssured.given()
           .contentType(ContentType.JSON)
           .body(dto)
        .when()
           .post("/cor")
        .then()
           .statusCode(200)
              .body("id", CoreMatchers.notNullValue(),
                "nome", CoreMatchers.is("Prata"));
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testAlterarCor() {
        CorDTO dto = new CorDTO("Dourado");

        CorDTOResponse corCriada = corService.create(dto);

        CorDTO dtoAlterar = new CorDTO("Ouro");

        RestAssured.given()
           .contentType(ContentType.JSON)
           .body(dtoAlterar)
        .when()
           .put("/cor/" + corCriada.id())
        .then()
           .statusCode(204);

        CorDTOResponse corAlterada = corService.findById(corCriada.id());
        assertEquals(dtoAlterar.nome(), corAlterada.nome());
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testApagarCor() {
        CorDTO dto = new CorDTO("Bronze");

        CorDTOResponse corCriada = corService.create(dto);

        RestAssured.given()
        .when()
           .delete("/cor/" + corCriada.id())
        .then()
           .statusCode(204);

        CorDTOResponse corDeletada = corService.findById(corCriada.id());
        assertNull(corDeletada);
    }
}
