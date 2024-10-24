package br.unitins.tp1.capinha;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.capinha.dto.EstoqueRequestDTO;
import br.unitins.tp1.capinha.model.Estoque;
import br.unitins.tp1.capinha.resource.EstoqueResource;
import br.unitins.tp1.capinha.service.EstoqueService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
@TestHTTPEndpoint(EstoqueResource.class)
public class EstoqueResourceTest {

    @Inject
    EstoqueService estoqueService;

    @Test
    public void testFindAll() {
        given()
            .when().get()
            .then()
                .statusCode(200);
    }

    @Test
    public void testCreate() {
        EstoqueRequestDTO dto = new EstoqueRequestDTO("Produto Teste", 50);

        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when()
                .post()
            .then()
                .statusCode(201)
                .body("id", notNullValue(),
                      "nomeProduto", is("Produto Teste"),
                      "quantidade", is(50));

        // Limpeza dos dados
        Estoque estoque = estoqueService.findByNomeProduto("Produto Teste").get(0);
        estoqueService.delete(estoque.getId());
    }

    @Test
    public void testUpdate() {
        // Inserindo dado para atualização
        EstoqueRequestDTO dto = new EstoqueRequestDTO("Produto Teste", 50);
        Long id = estoqueService.create(dto).getId();

        EstoqueRequestDTO novoDto = new EstoqueRequestDTO("Produto Atualizado", 100);

        given()
            .contentType(ContentType.JSON)
            .body(novoDto)
            .when()
                .put("/" + id)
            .then()
                .statusCode(204);

        Estoque estoqueAtualizado = estoqueService.findById(id);
        assertEquals("Produto Atualizado", estoqueAtualizado.getNomeProduto());
        assertEquals(100, estoqueAtualizado.getQuantidade());

        // Limpeza dos dados
        estoqueService.delete(id);
    }

    @Test
    public void testDelete() {
        // Inserindo dado para deleção
        EstoqueRequestDTO dto = new EstoqueRequestDTO("Produto Teste", 50);
        Long id = estoqueService.create(dto).getId();

        given()
            .when()
                .delete("/" + id)
            .then()
                .statusCode(204);

        
        Estoque estoque = estoqueService.findById(id);
        assertNull(estoque);
    }

    @Test
    public void testFindById() {
        // Inserindo dado
        EstoqueRequestDTO dto = new EstoqueRequestDTO("Produto Teste", 50);
        Long id = estoqueService.create(dto).getId();

        given()
            .when()
                .get("/" + id)
            .then()
                .statusCode(200)
                .body("id", is(id.intValue()),
                      "nomeProduto", is("Produto Teste"),
                      "quantidade", is(50));

        
        estoqueService.delete(id);
    }
}