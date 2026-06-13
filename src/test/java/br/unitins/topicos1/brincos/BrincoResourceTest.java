package br.unitins.topicos1.brincos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.brincos.dto.BrincoDTO;
import br.unitins.topicos1.brincos.dto.BrincoDTOResponse;
import br.unitins.topicos1.brincos.dto.CorDTO;
import br.unitins.topicos1.brincos.dto.CorDTOResponse;
import br.unitins.topicos1.brincos.dto.FormatoDTO;
import br.unitins.topicos1.brincos.dto.FormatoDTOResponse;
import br.unitins.topicos1.brincos.dto.MaterialDTO;
import br.unitins.topicos1.brincos.dto.MaterialDTOResponse;
import br.unitins.topicos1.brincos.service.BrincoService;
import br.unitins.topicos1.brincos.service.CorService;
import br.unitins.topicos1.brincos.service.FormatoService;
import br.unitins.topicos1.brincos.service.MaterialService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
class BrincoResourceTest {
    
   @Inject
   BrincoService brincoService;
   @Inject
   FormatoService formatoService;
   @Inject
   CorService corService;
   @Inject
   MaterialService materialService;

    @Test
    void testBuscarTodos() {
        RestAssured.given()
        .when().get("/brincos")
        .then()
           .statusCode(200);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testIncluirBrinco() {
         FormatoDTO dtoformato = new FormatoDTO("Redondo");
         FormatoDTOResponse novoformato = formatoService.create(dtoformato);
         
         CorDTO dtocor = new CorDTO("Vermelho");
         CorDTOResponse novacor = corService.create(dtocor);
         
         MaterialDTO dtomaterial = new MaterialDTO("Ouro");
         MaterialDTOResponse novamaterial = materialService.create(dtomaterial);

         /*
            String nome,
            Float tamanho,
            Float preco,
            Long idFormato,
            Long idColecao,
            List<Long> idsCores,
            List<Long> idsMateriais
         */

         BrincoDTO dto = new BrincoDTO("Argola", 10.0f, novoformato.id(), novacor.id(), novamaterial.id());
         
         Long brincoId = RestAssured.given()
            .contentType(ContentType.JSON)
            .body(dto)
         .when()
            .post("/brincos")
         .then()
            .statusCode(200)
            .extract()
            .jsonPath()
            .getLong("id");

         RestAssured.given()
            .when()
            .delete("/brincos/" + brincoId)
            .then()
            .statusCode(204);

         formatoService.delete(novoformato.id());
         corService.delete(novacor.id());
         materialService.delete(novamaterial.id());
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testAlterarBrinco() {
         FormatoDTO dtoformato = new FormatoDTO("Redondo");
         FormatoDTOResponse novoformato = formatoService.create(dtoformato);
         CorDTO dtocor = new CorDTO("Dourado");
         CorDTOResponse novacor = corService.create(dtocor);
         MaterialDTO dtomaterial = new MaterialDTO("Ouro");
         MaterialDTOResponse novamaterial = materialService.create(dtomaterial);
         BrincoDTO dto = new BrincoDTO("Argola", 10.0f, novoformato.id(), novacor.id(), novamaterial.id());

         BrincoDTOResponse brincoCriado = brincoService.create(dto);

         BrincoDTO dtoAlterar = new BrincoDTO("Argola Pequena", 8.0f, novoformato.id(), novacor.id(), novamaterial.id());

         RestAssured.given()
            .contentType(ContentType.JSON)
            .body(dtoAlterar)
         .when()
            .put("/brincos/" + brincoCriado.id())
         .then()
            .statusCode(204);

         BrincoDTOResponse brincoAlterado = brincoService.findById(brincoCriado.id());
         assertEquals(dtoAlterar.nome(), brincoAlterado.nome());

         RestAssured.given()
            .when()
            .delete("/brincos/" + brincoAlterado.id())
            .then()
            .statusCode(204);

         formatoService.delete(novoformato.id());
         corService.delete(novacor.id());
         materialService.delete(novamaterial.id());
    }

    @Test
    @TestSecurity(user = "testUser", roles = {"User"})
    void testApagarBrinco() {
         FormatoDTO dtoformato = new FormatoDTO("Redondo");
         FormatoDTOResponse novoformato = formatoService.create(dtoformato);
         CorDTO dtocor = new CorDTO("Bronze");
         CorDTOResponse novacor = corService.create(dtocor);
         MaterialDTO dtomaterial = new MaterialDTO("Bronze");
         MaterialDTOResponse novamaterial = materialService.create(dtomaterial);
         BrincoDTO dto = new BrincoDTO("Argola Bronze", 12.0f, novoformato.id(), novacor.id(), novamaterial.id());

         BrincoDTOResponse brincoCriado = brincoService.create(dto);

         RestAssured.given()
         .when()
            .delete("/brincos/" + brincoCriado.id())
         .then()
            .statusCode(204);

         BrincoDTOResponse brincoDeletada = brincoService.findById(brincoCriado.id());
         assertNull(brincoDeletada);

         formatoService.delete(novoformato.id());
         corService.delete(novacor.id());
         materialService.delete(novamaterial.id());
    }
}
