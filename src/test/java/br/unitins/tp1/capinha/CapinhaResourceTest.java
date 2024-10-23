package br.unitins.tp1.capinha;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.capinha.dto.CapinhaRequestDTO;
import br.unitins.tp1.capinha.model.Capinha;
import br.unitins.tp1.capinha.resource.CapinhaResource;
import br.unitins.tp1.capinha.service.CapinhaService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(CapinhaResource.class)
public class CapinhaResourceTest {

    @Inject
    CapinhaService capinhaService;

    @Test
    public void testFindAll() {
        given()
            .when().get()
            .then()
                .statusCode(200);
    }

    @Test
    public void testCreate() {
        CapinhaRequestDTO dto = new CapinhaRequestDTO("Proteção Rígida", 29.99f, "Capinha resistente a impactos");

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post()
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "tipoProtecao", is("Proteção Rígida"),
                      "preco", is(29.99f),
                      "descricao", is("Capinha resistente a impactos"));

        // Limpeza dos dados
        Capinha capinha = capinhaService.findByTipoProtecao("Proteção Rígida").get(0);
        capinhaService.delete(capinha.getId());
    }

    @Test
    public void testUpdate() {
        // Inserindo dado para atualização
        CapinhaRequestDTO dto = new CapinhaRequestDTO("Proteção Flexível", 19.99f, "Capinha de silicone");

        Long id = capinhaService.create(dto).getId();

        CapinhaRequestDTO novoDto = new CapinhaRequestDTO("Proteção Ultra Rígida", 39.99f, "Capinha com proteção adicional");

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/" + id)
            .then()
                .statusCode(204);

        Capinha capinhaAtualizada = capinhaService.findById(id);
        assertEquals("Proteção Ultra Rígida", capinhaAtualizada.getTipoProtecao());
        assertEquals(39.99f, capinhaAtualizada.getPreco());

        // Limpeza dos dados
        capinhaService.delete(id);
    }

    @Test
    public void testDelete() {
        // Inserindo dado para deleção
        CapinhaRequestDTO dto = new CapinhaRequestDTO("Proteção Leve", 9.99f, "Capinha simples");

        Long id = capinhaService.create(dto).getId();

        given()
            .when()
                .delete("/" + id)
            .then()
                .statusCode(204);

        // Verificando se foi apagado
        Capinha capinha = capinhaService.findById(id);
        assertNull(capinha);
    }

    @Test
    public void testFindById() {
        // Inserindo dado
        CapinhaRequestDTO dto = new CapinhaRequestDTO("Proteção Padrão", 19.99f, "Capinha padrão");

        Long id = capinhaService.create(dto).getId();

        given()
            .when()
                .get("/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()),
                      "tipoProtecao", is("Proteção Padrão"),
                      "preco", is(19.99f),
                      "descricao", is("Capinha padrão"));

        // Limpeza dos dados
        capinhaService.delete(id);
    }
}