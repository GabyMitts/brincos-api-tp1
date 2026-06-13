package br.unitins.topicos1.brincos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.brincos.dto.CorDTO;
import br.unitins.topicos1.brincos.dto.CorDTOResponse;
import br.unitins.topicos1.brincos.dto.FormatoDTO;
import br.unitins.topicos1.brincos.dto.FormatoDTOResponse;
import br.unitins.topicos1.brincos.dto.MaterialDTO;
import br.unitins.topicos1.brincos.dto.MaterialDTOResponse;
import br.unitins.topicos1.brincos.dto.PiercingDTO;
import br.unitins.topicos1.brincos.dto.PiercingDTOResponse;
import br.unitins.topicos1.brincos.service.CorService;
import br.unitins.topicos1.brincos.service.FormatoService;
import br.unitins.topicos1.brincos.service.MaterialService;
import br.unitins.topicos1.brincos.service.PiercingService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
class PiercingResourceTest {
    
   @Inject
   PiercingService piercingService;
   @Inject
   FormatoService formatoService;
   @Inject
   CorService corService;
   @Inject
   MaterialService materialService;

    @Test
    void testBuscarTodos() {
        RestAssured.given()
        .when().get("/piercings")
        .then()
           .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testIncluirPiercing() {
         FormatoDTO dtoformato = new FormatoDTO("Redondo");
         FormatoDTOResponse novoformato = formatoService.create(dtoformato);
         
         CorDTO dtocor = new CorDTO("Vermelho");
         CorDTOResponse novacor = corService.create(dtocor);
         
         MaterialDTO dtomaterial = new MaterialDTO("Ouro");
         MaterialDTOResponse novamaterial = materialService.create(dtomaterial);

         PiercingDTO dto = new PiercingDTO("Argola", 10.0f, novoformato.id(), novacor.id(), novamaterial.id(), "ORELHA");
         
         Long piercingId = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(dto)
         .when()
            .post("/piercings")
         .then()
            .statusCode(200)
            .extract()
            .jsonPath()
            .getLong("id");

         RestAssured.given()
            .when()
            .delete("/piercings/" + piercingId)
            .then()
            .statusCode(204);

         formatoService.delete(novoformato.id());
         corService.delete(novacor.id());
         materialService.delete(novamaterial.id());
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testAlterarPiercing() {
         FormatoDTO dtoformato = new FormatoDTO("Redondo");
         FormatoDTOResponse novoformato = formatoService.create(dtoformato);
         CorDTO dtocor = new CorDTO("Dourado");
         CorDTOResponse novacor = corService.create(dtocor);
         MaterialDTO dtomaterial = new MaterialDTO("Ouro");
         MaterialDTOResponse novamaterial = materialService.create(dtomaterial);
         PiercingDTO dto = new PiercingDTO("Argola", 10.0f, novoformato.id(), novacor.id(), novamaterial.id(), "ORELHA");

         PiercingDTOResponse piercingCriado = piercingService.create(dto);

         PiercingDTO dtoAlterar = new PiercingDTO("Argola Pequena", 8.0f, novoformato.id(), novacor.id(), novamaterial.id(), "ORELHA");

         RestAssured.given()
            .contentType(ContentType.JSON)
            .body(dtoAlterar)
         .when()
            .put("/piercings/" + piercingCriado.id())
         .then()
            .statusCode(204);

         PiercingDTOResponse piercingAlterado = piercingService.findById(piercingCriado.id());
         assertEquals(dtoAlterar.nome(), piercingAlterado.nome());

         RestAssured.given()
            .when()
            .delete("/piercings/" + piercingAlterado.id())
            .then()
            .statusCode(204);

         formatoService.delete(novoformato.id());
         corService.delete(novacor.id());
         materialService.delete(novamaterial.id());
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testApagarPiercing() {
         FormatoDTO dtoformato = new FormatoDTO("Redondo");
         FormatoDTOResponse novoformato = formatoService.create(dtoformato);
         CorDTO dtocor = new CorDTO("Bronze");
         CorDTOResponse novacor = corService.create(dtocor);
         MaterialDTO dtomaterial = new MaterialDTO("Bronze");
         MaterialDTOResponse novamaterial = materialService.create(dtomaterial);
         PiercingDTO dto = new PiercingDTO("Argola Bronze", 12.0f, novoformato.id(), novacor.id(), novamaterial.id(), "ORELHA");

         PiercingDTOResponse piercingCriado = piercingService.create(dto);

         RestAssured.given()
         .when()
            .delete("/piercings/" + piercingCriado.id())
         .then()
            .statusCode(204);

         PiercingDTOResponse brincoDeletada = piercingService.findById(piercingCriado.id());
         assertNull(brincoDeletada);

         formatoService.delete(novoformato.id());
         corService.delete(novacor.id());
         materialService.delete(novamaterial.id());
    }
}
