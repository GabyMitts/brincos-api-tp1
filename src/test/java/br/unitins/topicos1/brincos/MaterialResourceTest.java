package br.unitins.topicos1.brincos;

import org.hamcrest.CoreMatchers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.brincos.dto.MaterialDTO;
import br.unitins.topicos1.brincos.dto.MaterialDTOResponse;
import br.unitins.topicos1.brincos.service.MaterialService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
class MaterialResourceTest {
    
    @Inject
    MaterialService materialService;

    @Test
    void testBuscarTodos() {
        RestAssured.given()
        .when().get("/material")
        .then()
           .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testIncluirMaterial() {
        MaterialDTO dto = new MaterialDTO("Prata");

        RestAssured.given()
           .contentType(ContentType.JSON)
           .body(dto)
        .when()
           .post("/material")
        .then()
           .statusCode(200)
              .body("id", CoreMatchers.notNullValue(),
                "nome", CoreMatchers.is("Prata"));
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testAlterarMaterial() {
        MaterialDTO dto = new MaterialDTO("Cobre");

        MaterialDTOResponse materialCriado = materialService.create(dto);

        MaterialDTO dtoAlterar = new MaterialDTO("Ouro");

        RestAssured.given()
           .contentType(ContentType.JSON)
           .body(dtoAlterar)
        .when()
           .put("/material/" + materialCriado.id())
        .then()
           .statusCode(204);

        MaterialDTOResponse materialAlterado = materialService.findById(materialCriado.id());
        assertEquals(dtoAlterar.nome(), materialAlterado.nome());
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testApagarMaterial() {
        MaterialDTO dto = new MaterialDTO("Bronze");

        MaterialDTOResponse materialCriado = materialService.create(dto);

        RestAssured.given()
        .when()
           .delete("/material/" + materialCriado.id())
        .then()
           .statusCode(204);

        MaterialDTOResponse materialDeletado = materialService.findById(materialCriado.id());
        assertNull(materialDeletado);
    }
}
